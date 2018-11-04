package care.com.careOff.Service

import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService

class CareOffMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String?) {
        super.onNewToken(p0)
        Log.d("AAAA", "Refreshed token: " + p0);
    }
}