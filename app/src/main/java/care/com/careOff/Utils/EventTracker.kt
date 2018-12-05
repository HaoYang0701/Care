package care.com.careOff.Utils

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

class EventTracker {
    companion object {
        fun sendPageOpenEvent(isOpened : Boolean, context: Context) {
            val firebaseAnalytics = FirebaseAnalytics.getInstance(context)
            val params = Bundle()
            params.putString("page_opened", isOpened.toString())
            firebaseAnalytics.logEvent("PAGE_EVENT", params)
        }
    }
}