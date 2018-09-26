package care.com.care.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as LoginFragment? ?: LoginFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }

        loginPresenter = LoginPresenter(loginFragment)
    }
}