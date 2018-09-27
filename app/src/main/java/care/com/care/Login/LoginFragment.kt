package care.com.care.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import care.com.care.R
import care.com.care.Registration.RegistrationActivity
import care.com.care.databinding.LoginFragmentBinding


class LoginFragment : LoginContract.View, Fragment() {
    override fun goToForgotPasswordScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun goToRegistrationScreen() {
        startActivity(Intent(context, RegistrationActivity::class.java))
    }

    override fun goToHomeScreen() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private lateinit var presenter: LoginContract.Presenter
    private lateinit var viewBinding : LoginFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.login_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.loginButton.setOnClickListener { presenter.onLoginButtonClicked() }
        viewBinding.registerButton.setOnClickListener { presenter.onRegistrationClicked() }
        viewBinding.forgotPasswordButton.setOnClickListener { presenter.onForgotPasswordClicked() }
    }

    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        this.presenter = presenter
    }


    companion object {
        fun newInstance() : LoginFragment{
            return LoginFragment()
        }
    }


}