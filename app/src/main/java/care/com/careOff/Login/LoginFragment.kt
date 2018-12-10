package care.com.careOff.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.login.welcome.WelcomeActivity
import care.com.careOff.R
import care.com.careOff.databinding.LoginFragmentBinding
import care.com.careOff.home.HomeActivity
import care.com.careOff.login.twoFactorAuth.TwoFactorActivity
import care.com.careOff.passwordreset.PasswordResetActivity
import com.google.android.material.snackbar.Snackbar

class LoginFragment : LoginContract.View, Fragment() {

    override fun showLoginError() {
        val snackbar = Snackbar.make(activity!!.findViewById(R.id.login_container),
                "The phone number and password does not match", Snackbar.LENGTH_LONG)
        snackbar.show()
    }

    override fun goToForgotPasswordScreen() {
        startActivity(Intent(context, PasswordResetActivity::class.java))
    }

    override fun goToWelcomeScreen() {
        startActivity(Intent(context, WelcomeActivity::class.java))
    }

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var viewBinding: LoginFragmentBinding
    private lateinit var observable: LoginObservable

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        val view = viewBinding.root
        viewBinding.state = this.observable
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.loginButton.setOnClickListener { v -> presenter.onLoginButtonClicked() }
        viewBinding.cancelAction.setOnClickListener { v -> presenter.onCancelClicked() }
        viewBinding.forgotPasswordAction.setOnClickListener { v -> presenter.onForgotPasswordClicked() }
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun goToHomeScreen() {
        val intent = Intent(context, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
    }

    override fun setObservable(observable: LoginObservable) {
        this.observable = observable
    }

    companion object {
        fun newInstance(): LoginFragment {
            return LoginFragment()
        }
    }
}