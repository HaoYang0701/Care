package care.com.careOff.registration

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import care.com.careOff.R
import care.com.careOff.Utils.replaceFragmentInActivity
import com.jakewharton.threetenabp.AndroidThreeTen

class RegistrationActivity : AppCompatActivity() {
    private lateinit var presenter: RegistrationPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.registration_activity)
        AndroidThreeTen.init(this)
        val registrationFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as RegistrationFragment? ?: RegistrationFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }
        presenter = RegistrationPresenter(registrationFragment)
    }
}