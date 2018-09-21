package care.com.care.Login

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import care.com.care.R

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        var loginFragment : LoginFragment? =
                supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as LoginFragment ?: LoginFragment.newInstance().also {
                    replace
                }

    }
}