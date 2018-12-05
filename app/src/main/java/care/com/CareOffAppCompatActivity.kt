package care.com

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.Utils.EventTracker

open class CareOffAppCompatActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        EventTracker.sendPageOpenEvent(true, this)
    }

    override fun onDestroy() {
        super.onDestroy()
        EventTracker.sendPageOpenEvent(false, this)
    }
}