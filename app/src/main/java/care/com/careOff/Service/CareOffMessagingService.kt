package care.com.careOff.Service

import android.app.PendingIntent
import android.content.Intent
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import care.com.careOff.Model.PushTokenUpdateResponse
import care.com.careOff.Network.PushTokenUpdateRequest
import care.com.careOff.R
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import care.com.careOff.home.HomeActivity
import care.com.careOff.shift.ShiftDetailsActivity
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import java.util.*

class CareOffMessagingService : FirebaseMessagingService() {
    override fun onNewToken(token: String?) {
        super.onNewToken(token)
        if (token != null) {
            val preferences = SharedPref(applicationContext)
            preferences.update("deviceToken", token)
            System.out.println("Device Token " + token)
            // Tokens only update on new app / cache clears. So theres no way the user would have any of these tokens stored. They have not even registered yet
        }
    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {
        super.onMessageReceived(remoteMessage)
        remoteMessage
        if (remoteMessage?.data != null && remoteMessage?.data.isNotEmpty()) {
            Log.d("MESSAGE", "Message data payload: " + remoteMessage.data)
            createPushNotification(remoteMessage.data.get("id"))
        }
}

    private fun createPushNotification(string: String?) {
        val random = Random().nextInt()
        val intent = Intent(applicationContext, ShiftDetailsActivity::class.java)
        intent.putExtra("shift_id", string?.toInt())
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_SINGLE_TOP)
        val pendingIntent = PendingIntent.getActivity(applicationContext, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)


        var mBuilder = NotificationCompat.Builder(this, "default")
                .setSmallIcon(R.drawable.logo_horizontal)
                .setContentTitle("CareOff")
                .setContentText(string)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

        with(NotificationManagerCompat.from(this)) {
            notify(random, mBuilder.build())
        }
    }
}