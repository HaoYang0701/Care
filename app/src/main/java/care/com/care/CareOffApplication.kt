package care.com.care

import android.app.Application
import android.content.Context

class CareOffApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        CareOffApplication.context = applicationContext
    }

    companion object {
        var context: Context? = null

        fun getAppContext() : Context? {
            return context
        }
    }
}