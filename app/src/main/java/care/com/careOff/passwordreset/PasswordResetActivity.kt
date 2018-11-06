package care.com.careOff.passwordreset

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.replaceFragmentInActivity

class PasswordResetActivity : AppCompatActivity(){

    private lateinit var passwordResetPresenter: PasswordResetPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.password_reset_activity)


        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as PasswordResetFragment? ?: PasswordResetFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        passwordResetPresenter = PasswordResetPresenter(loginFragment)

        loginFragment.setPresenter(passwordResetPresenter)
    }
}