package care.com.careOff.shift

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.CareOffAppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.replaceFragmentInActivity

class ShiftActivity : CareOffAppCompatActivity() {
    private lateinit var presenter: ShiftPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.shift_activity)

        val shiftFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ShiftFragment? ?: ShiftFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        presenter = ShiftPresenter(shiftFragment)
    }
}