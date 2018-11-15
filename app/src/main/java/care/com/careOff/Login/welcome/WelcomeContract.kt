package care.com.careOff.login.welcome

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface WelcomeContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()

        fun goToRegistrationScreen()

        fun goToSettings()

        fun goToSignIn()
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()

        fun onRegistrationClicked()

        fun onFabClicked()
    }
}