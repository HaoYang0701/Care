package care.com.careOff.registration

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import care.com.careOff.R
import care.com.careOff.databinding.RegistrationFragmentBinding
import care.com.careOff.home.HomeActivity
import android.widget.Toast
import care.com.careOff.login.LoginActivity
import care.com.careOff.login.twoFactorAuth.TwoFactorActivity

class RegistrationFragment : RegistrationContract.View, Fragment(){

    override fun goToLogin() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    override fun goToTwoFactor() {
        startActivity(Intent(context, TwoFactorActivity::class.java))
    }


    override fun showToast(string : String) {
        Toast.makeText(activity, string,
                Toast.LENGTH_LONG).show()
    }

    override fun setConfirmPasswordError() {
        viewBinding.textInputLayoutConfirmPassword.error = "Your password does not match"
    }

    override fun setPasswordError() {
        viewBinding.textInputLayoutPassword.error = "Please try a stronger password"
    }

    override fun setDobError() {
        viewBinding.dobError.visibility = View.VISIBLE
    }

    override fun setPhoneNumberError() {
        viewBinding.textInputLayoutPhone.error = "Invalid Phone Number"
    }

    override fun setZipCodeError() {
        viewBinding.textInputLayoutZipCode.error = "Invalid Zipcode"
    }

    override fun setEmailError() {
        viewBinding.textInputLayoutEmail.error = "Invalid Email"
    }

    override fun setLastNameError() {
        viewBinding.textInputLayoutLastName.error = "Last name must not be blank"
    }

    override fun setFirstNameError() {
        viewBinding.textInputLayoutFirstName.error = "First name must not be blank"
    }


    override fun goToHomeScreen() {
        startActivity(Intent(context, HomeActivity::class.java))
    }

    private lateinit var presenter: RegistrationContract.Presenter
    private lateinit var viewBinding : RegistrationFragmentBinding
    private lateinit var observable: RegistrationObservable

    override fun setPresenter(presenter: RegistrationContract.Presenter) {
        this.presenter = presenter
    }

    override fun setObservable(observable: RegistrationObservable) {
        this.observable = observable
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.registration_fragment, container, false)
        val view : View = viewBinding.root
        viewBinding.presenter = this.presenter
        viewBinding.state = this.observable
        viewBinding.logInAction.setOnClickListener{v -> presenter.LoginActionClicked()}
        return view
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    companion object {
        fun newInstance() : RegistrationFragment{
            return RegistrationFragment()
        }
    }
}