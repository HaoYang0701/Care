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
import org.joda.time.DateTime

class RegistrationFragment : RegistrationContract.View, Fragment(){
    override fun clearConfirmPasswordError() {
        viewBinding.textInputLayoutConfirmPassword.isErrorEnabled = false
    }

    override fun clearPasswordError() {
        viewBinding.textInputLayoutPassword.isErrorEnabled = false
        viewBinding.textInputLayoutConfirmPassword.isErrorEnabled = false
    }

    override fun clearDobError() {
        viewBinding.dobError.visibility = View.GONE
    }

    override fun clearPhoneNumberError() {
        viewBinding.textInputLayoutPhone.isErrorEnabled = false
    }

    override fun clearZipCodeError() {
        viewBinding.textInputLayoutZipCode.isErrorEnabled = false
    }

    override fun clearEmailError() {
        viewBinding.textInputLayoutEmail.isErrorEnabled = false
    }

    override fun clearLastNameError() {
        viewBinding.textInputLayoutLastName.isErrorEnabled = false
    }

    override fun clearFirstNameError() {
        viewBinding.textInputLayoutFirstName.isErrorEnabled = false
    }

    override fun goToLogin() {
        startActivity(Intent(context, LoginActivity::class.java))
        activity?.finish()
        return
    }

    override fun goToTwoFactor() {
        startActivity(Intent(context, TwoFactorActivity::class.java))
        activity?.finish()
        return
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
        activity?.finish()
        return
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
        viewBinding.datePicker.maxDate = DateTime().minusYears(18).millis

        viewBinding.editFirstName.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidFirstName()
            }
        }

        viewBinding.editLastName.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidLastName()
            }
        }

        viewBinding.editEmail.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidEmail()
            }
        }
        viewBinding.editZip.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidZip()
            }
        }

        viewBinding.editConfirmPassword.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidPassword()
            }
        }

        viewBinding.editPhone.onFocusChangeListener = View.OnFocusChangeListener { v, hasFocus ->
            if (!hasFocus) {
                presenter.checkValidPhone()
            }
        }


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

    override fun showRegisterButton(shouldShowLogin: Boolean) {
        if (shouldShowLogin) {
            viewBinding.registerButton.visibility = View.VISIBLE
        } else {
            viewBinding.registerButton.visibility = View.GONE
        }
    }

    override fun showSpinner(shouldShowSpinner: Boolean) {
        if (shouldShowSpinner) {
            viewBinding.progressBar.visibility = View.VISIBLE
        } else {
            viewBinding.progressBar.visibility = View.GONE
        }
    }

    companion object {
        fun newInstance() : RegistrationFragment{
            return RegistrationFragment()
        }
    }
}