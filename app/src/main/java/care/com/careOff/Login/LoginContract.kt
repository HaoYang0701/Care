package care.com.careOff.login

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()
        fun goToWelcomeScreen()
        fun goToForgotPasswordScreen()
        fun setObservable(observable: LoginObservable)
        fun showLoginError()
        fun showSpinner(boolean: Boolean)
        fun showLoginButton(boolean: Boolean)
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()
        fun onCancelClicked()
        fun onForgotPasswordClicked()
    }
}