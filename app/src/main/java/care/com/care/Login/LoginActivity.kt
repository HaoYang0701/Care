package care.com.care.Login

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity
import com.google.firebase.analytics.FirebaseAnalytics

class LoginActivity : AppCompatActivity() {
    private lateinit var loginPresenter: LoginPresenter
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)

        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as LoginFragment? ?: LoginFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)
        loginPresenter = LoginPresenter(loginFragment)
    }
}