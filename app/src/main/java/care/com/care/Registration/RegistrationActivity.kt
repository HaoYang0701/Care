package care.com.care.Registration

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val registrationFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as RegistrationFragment? ?: RegistrationFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        this.presenter = RegistrationPresenter(registrationFragment)
    }
}