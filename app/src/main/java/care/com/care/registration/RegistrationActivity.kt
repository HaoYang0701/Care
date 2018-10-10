package care.com.care.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
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

        val loginViewModel = ViewModelProviders.of(this).get(RegistrationViewModel::class.java)
        presenter = RegistrationPresenter(registrationFragment)
    }
}