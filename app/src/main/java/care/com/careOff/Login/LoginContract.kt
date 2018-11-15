package care.com.careOff.login

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()
        fun goToWelcomeScreen()
        fun goToForgotPasswordScreen()
        fun setObservable(observable: LoginObservable)
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()
        fun onCancelClicked()
        fun onForgotPasswordClicked()
    }
}