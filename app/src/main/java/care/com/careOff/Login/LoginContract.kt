package care.com.careOff.Login

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()
        fun goToWelcomeScreen()
        fun goToForgotPasswordScreen()
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()
        fun onCancelClicked()
        fun onForgotPasswordClicked()
    }
}