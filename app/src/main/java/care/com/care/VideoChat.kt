package care.com.care

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import org.webrtc.*
import org.webrtc.EglBase
import org.webrtc.SurfaceViewRenderer




class VideoChat : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_video_chat)

        PeerConnectionFactory.initialize(PeerConnectionFactory.InitializationOptions.builder(this).createInitializationOptions())
        val peerConnectionFactory = PeerConnectionFactory.builder().createPeerConnectionFactory()

        val videoCapturerAndroid = initVideoCapture()
        val constraints = MediaConstraints()


        val videoSource : VideoSource = peerConnectionFactory.createVideoSource(true)
        val localVideoTrack : VideoTrack = peerConnectionFactory.createVideoTrack("100", videoSource)

        val audioSource = peerConnectionFactory.createAudioSource(constraints)
        val localAudioTrack = peerConnectionFactory.createAudioTrack("101", audioSource)

        videoCapturerAndroid?.startCapture(1000, 1000, 30)

        val videoView = findViewById(R.id.surface_rendeer) as SurfaceViewRenderer
        videoView.setMirror(true)

        val rootEglBase = EglBase.create()
        videoView.init(rootEglBase.eglBaseContext, null)

        localVideoTrack.add(VideoRenderer())
    }

    private fun initVideoCapture() : VideoCapturer? {
        val videoCapturer : VideoCapturer? = createCameraCapturer(Camera1Enumerator(false))
        return videoCapturer
    }

    private fun createCameraCapturer(enumerator : Camera1Enumerator) : VideoCapturer? {
        val deviceNames : Array<String> = enumerator.deviceNames

        for (deviceName : String in deviceNames) {
            if (enumerator.isFrontFacing(deviceName)) {
                val videoCapturer = enumerator.createCapturer(deviceName, null)

                if (videoCapturer != null) {
                    return videoCapturer
                }
            }
        }

        for (deviceName : String in deviceNames) {
            if (!enumerator.isFrontFacing(deviceName)) {
                val videoCapturer = enumerator.createCapturer(deviceName, null)

                if (videoCapturer != null) {
                    return videoCapturer
                }
            }
        }
        return null
    }
}
