package care.com.care.Registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity

class RegistrationActivity : AppCompatActivity() {
    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        val registrationFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as RegistrationFragment? ?: RegistrationFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        presenter = RegistrationPresenter(registrationFragment)
    }
}