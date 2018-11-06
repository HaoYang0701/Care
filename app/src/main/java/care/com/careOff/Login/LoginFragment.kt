package care.com.careOff.Login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.Login.welcome.WelcomeActivity
import care.com.careOff.R
import care.com.careOff.databinding.LoginFragmentBinding
import care.com.careOff.home.HomeActivity
import care.com.careOff.passwordreset.PasswordResetActivity

class LoginFragment : LoginContract.View, Fragment() {
    override fun goToForgotPasswordScreen() {
        startActivity(Intent(context, PasswordResetActivity::class.java))
    }

    override fun goToWelcomeScreen() {
        startActivity(Intent(context, WelcomeActivity::class.java))
    }

    private lateinit var presenter : LoginContract.Presenter
    private lateinit var viewBinding : LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.loginButton.setOnClickListener{v -> presenter.onLoginButtonClicked()}
        viewBinding.cancelAction.setOnClickListener{v -> presenter.onCancelClicked()}
        viewBinding.forgotPasswordAction.setOnClickListener{v -> presenter.onForgotPasswordClicked()}
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }

    override fun goToHomeScreen() {
       startActivity(Intent(context, HomeActivity::class.java))
    }

    companion object {
        fun newInstance() : LoginFragment {
            return LoginFragment()
        }
    }
}