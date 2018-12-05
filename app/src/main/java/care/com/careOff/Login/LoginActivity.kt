package care.com.careOff.login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.CareOffAppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.EventTracker
import care.com.careOff.Utils.SharedPref
import care.com.careOff.Utils.replaceFragmentInActivity


class LoginActivity : CareOffAppCompatActivity(){

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as LoginFragment? ?: LoginFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        loginPresenter = LoginPresenter(loginFragment, SharedPref(this))

        loginFragment.setPresenter(loginPresenter)
    }
}