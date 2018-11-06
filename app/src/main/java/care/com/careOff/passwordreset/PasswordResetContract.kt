package care.com.careOff.passwordreset

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface PasswordResetContract {

    interface View : BaseView<Presenter> {
        fun goToLoginScreen()
        fun goToWelcomeScreen()
    }

    interface Presenter : BasePresenter {
        fun onResetButtonClicked()
        fun onCancelClicked()
    }
}