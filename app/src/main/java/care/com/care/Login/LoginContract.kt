package care.com.care.Login

import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()

        fun goToRegistrationScreen()

        fun goToForgotPasswordScreen()
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()

        fun onRegistrationClicked()

        fun onForgotPasswordClicked()
    }
}