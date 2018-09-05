package care.com.care

import android.util.Log
import org.json.JSONObject
import java.security.cert.X509Certificate
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager
import io.socket.client.IO;
import org.json.JSONException
import java.net.URISyntaxException
import java.security.KeyManagementException
import java.security.NoSuchAlgorithmException
import java.util.*
import org.webrtc.IceCandidate
import org.webrtc.SessionDescription



internal class SignalingClient {
    internal interface  SignalingInterface {
        fun onRemoteHangUp(msg : String)

        fun onOfferReceived(data : JSONObject)

        fun onAnswerReceived(data: JSONObject)

        fun onIceCandidateReceived(data: JSONObject)

        fun onTryToStart()

        fun onCreateRoom()

        fun onJoinedRoom()

        fun onNewPeerJoined()
    }

    companion object {
        private var roomName : String? = null

        init {
            if (roomName == null) {
                roomName = "a"
            }
        }

        private var socket : io.socket.client.Socket? = null
        var isChannelReady = false
        var isInitiator = false
        var isStarted = false
        private var callback : SignalingInterface? = null


        private val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
            override fun getAcceptedIssuers(): Array<X509Certificate> {
                return arrayOf()
            }

            override fun checkServerTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }

            override fun checkClientTrusted(p0: Array<out X509Certificate>?, p1: String?) {
            }
        })

        fun init(signalingInterface: SignalingInterface) {
            this.callback = signalingInterface

            try {
                val sslContext = SSLContext.getInstance("TLS")
                sslContext.init(null, trustAllCerts, null)
                IO.setDefaultHostnameVerifier{_,_ -> true}
                IO.setDefaultSSLContext(sslContext)
                socket  = IO.socket("https://hidden-hollows-28924.herokuapp.com/")
                socket?.connect()

                roomName?.let { emitInitStatement(it)}

                socket?.on("full") {
                    args -> Log.d("SignallingClient", "full call() called with: args = [" + Arrays.toString(args) + "]")
                }

                socket?.on("join") { args ->
                    Log.d("SignallingClient", "join call() called with: args = [" + Arrays.toString(args) + "]")
                    isChannelReady = true
                    callback?.onNewPeerJoined()
                }

                //when you joined a chat room successfully
                socket?.on("joined") { args ->
                    Log.d("SignallingClient", "joined call() called with: args = [" + Arrays.toString(args) + "]")
                    isChannelReady = true
                    callback?.onJoinedRoom()
                }

                //log event
                socket?.on("log") { args -> Log.d("SignallingClient", "log call() called with: args = [" + Arrays.toString(args) + "]") }

                //bye event
                socket?.on("bye") { args -> callback?.onRemoteHangUp(args[0] as String) }

                //messages - SDP and ICE candidates are transferred through this
                socket?.on("message") { args ->
                    Log.d("SignallingClient", "message call() called with: args = [" + Arrays.toString(args) + "]")
                    when (args[0]) {
                        is String -> {
                            Log.d("SignallingClient", "String received :: " + args[0])
                            val data = args[0] as String
                            if (data.equals("got user media", ignoreCase = true)) {
                                callback?.onTryToStart()
                            }
                            if (data.equals("bye", ignoreCase = true)) {
                                callback?.onRemoteHangUp(data)
                            }
                        }
                        is JSONObject -> try {
                            val data = args[0] as JSONObject
                            Log.d("SignallingClient", "Json Received :: " + data.toString())
                            val type = data.getString("type")
                            if (type.equals("offer", ignoreCase = true)) {
                                callback?.onOfferReceived(data)
                            } else if (type.equals("answer", ignoreCase = true) && isStarted) {
                                callback?.onAnswerReceived(data)
                            } else if (type.equals("candidate", ignoreCase = true) && isStarted) {
                                callback?.onIceCandidateReceived(data)
                            }

                        } catch (e: JSONException) {
                            e.printStackTrace()
                        }
                    }
                }
            }  catch (e: URISyntaxException) {
                e.printStackTrace()
            } catch (e: NoSuchAlgorithmException) {
                e.printStackTrace()
            } catch (e: KeyManagementException) {
                e.printStackTrace()
            }
        }

        private fun emitInitStatement(message: String) {
            Log.d("SignallingClient", "emitInitStatement() called with: event = [create or join], message = [$message]")
            socket?.emit("create or join", message)
        }

        fun close() {
            socket?.emit("bye", roomName)
            socket?.disconnect()
            socket?.close()
        }

        fun emitMessage(message: String) {
            Log.d("SignallingClient", "emitMessage() called with: message = [$message]")
            socket?.emit("message", message)
        }

        fun emitMessage(message: SessionDescription) {
            try {
                Log.d("SignallingClient", "emitMessage() called with: message = [$message]")
                val obj = JSONObject()
                obj.put("type", message.type.canonicalForm())
                obj.put("sdp", message.description)
                Log.d("emitMessage", obj.toString())
                socket?.emit("message", obj)
                Log.d("vivek1794", obj.toString())
            } catch (e: JSONException) {
                e.printStackTrace()
            }

        }


        fun emitIceCandidate(iceCandidate: IceCandidate) {
            try {
                val `object` = JSONObject()
                `object`.put("type", "candidate")
                `object`.put("label", iceCandidate.sdpMLineIndex)
                `object`.put("id", iceCandidate.sdpMid)
                `object`.put("candidate", iceCandidate.sdp)
                socket?.emit("message", `object`)
            } catch (e: Exception) {
                e.printStackTrace()
            }

        }
    }
}