package care.com.careOff.shift

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import care.com.CareOffAppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.SharedPref
import care.com.careOff.Utils.replaceFragmentInActivity

class ShiftDetailsActivity : CareOffAppCompatActivity() {
    private lateinit var presenter: ShiftDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.shift_activity)

        val myToolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(myToolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val shiftFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as ShiftDetailsFragment? ?: ShiftDetailsFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        val shiftId = intent.getIntExtra("shift_id", 0)
        presenter = ShiftDetailsPresenter(shiftFragment, shiftId, SharedPref(applicationContext))
    }
}