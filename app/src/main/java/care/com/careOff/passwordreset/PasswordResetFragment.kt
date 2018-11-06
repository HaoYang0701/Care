package care.com.careOff.passwordreset

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.careOff.Login.LoginActivity
import care.com.careOff.Login.welcome.WelcomeActivity
import care.com.careOff.R
import care.com.careOff.databinding.PasswordResetFragmentBinding

class PasswordResetFragment : PasswordResetContract.View, Fragment() {
    override fun goToWelcomeScreen() {
        startActivity(Intent(context, WelcomeActivity::class.java))
    }

    override fun goToLoginScreen() {
        startActivity(Intent(context, LoginActivity::class.java))
    }

    private lateinit var presenter : PasswordResetContract.Presenter
    private lateinit var viewBinding : PasswordResetFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.password_reset_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.passwordResetButton.setOnClickListener{v -> presenter.onResetButtonClicked()}
        viewBinding.cancelAction.setOnClickListener{v -> presenter.onCancelClicked()}
    }

    override fun setPresenter(presenter: PasswordResetContract.Presenter) {
        this.presenter = presenter
    }

    companion object {
        fun newInstance() : PasswordResetFragment {
            return PasswordResetFragment()
        }
    }
}