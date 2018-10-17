package care.com.care.Login.twoFactorAuth

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity

class TwoFactorActivity : AppCompatActivity(){

    private lateinit var twoFactorPresenter : TwoFactorPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.two_factor_activity)

        val twoFactorFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as TwoFactorFragment? ?: TwoFactorFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        twoFactorPresenter = TwoFactorPresenter(twoFactorFragment)

        twoFactorFragment.setPresenter(twoFactorPresenter)
    }

}