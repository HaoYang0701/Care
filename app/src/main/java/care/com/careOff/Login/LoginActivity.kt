package care.com.careOff.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.replaceFragmentInActivity


class LoginActivity : AppCompatActivity(){

    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)


        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as LoginFragment? ?: LoginFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        loginPresenter = LoginPresenter(loginFragment)

        loginFragment.setPresenter(loginPresenter)
    }
}