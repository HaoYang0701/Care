package care.com.careOff.Service

import android.util.Log
import care.com.careOff.Network.PushTokenUpdateRequest
import care.com.careOff.data.database.source.remote.RemoteDataSource
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage

class CareOffMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        if (token != null) {
            val tokenRequest = PushTokenUpdateRequest(token)
            RemoteDataSource.updateFirebaseToken(tokenRequest).subscribe{
                v ->
            }
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        if (remoteMessage?.data != null && remoteMessage?.data.isNotEmpty()) {
            Log.d("MESSAGE", "Message data payload: " + remoteMessage.data);

        }
}
}