package care.com.careOff.debug

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.SharedPref
import care.com.careOff.Utils.replaceFragmentInActivity

class DebugActivity : AppCompatActivity() {
    private lateinit var presenter : DebugPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.debug_activity)
        val debugFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as DebugFragment? ?: DebugFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        presenter = DebugPresenter(debugFragment, SharedPref(this))
    }
}