package care.com.careOff.Login

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {
        fun goToHomeScreen()

        fun goToRegistrationScreen()

        fun goToSettings()
    }

    interface Presenter : BasePresenter {
        fun onLoginButtonClicked()

        fun onRegistrationClicked()

        fun onFabClicked()
    }
}