package care.com.careOff.Login

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import care.com.careOff.R
import care.com.careOff.registration.RegistrationActivity
import care.com.careOff.databinding.LoginFragmentBinding
import care.com.careOff.documentupload.DocumentUploadActivity


class LoginFragment : LoginContract.View, Fragment() {
    override fun goToSettings() {
        startActivity(Intent(context, DocumentUploadActivity::class.java))
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


    override fun goToRegistrationScreen() {
        startActivity(Intent(context, RegistrationActivity::class.java))
    }

    override fun goToHomeScreen() {

    }

    companion object {
        fun newInstance() : LoginFragment{
            return LoginFragment()
        }
    }


}