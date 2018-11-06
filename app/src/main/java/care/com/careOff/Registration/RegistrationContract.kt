package care.com.careOff.registration

import androidx.annotation.StringRes
import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface RegistrationContract {
    interface View : BaseView<Presenter> {
        fun goToHomeScreen()

        fun goToTwoFactor()

        fun setFirstNameError()

        fun setLastNameError()

        fun setEmailError()

        fun setZipCodeError()

        fun setPhoneNumberError()

        fun setDobError()

        fun setPasswordError()

        fun setConfirmPasswordError()

        fun setObservable(observable: RegistrationObservable)

        fun showToast(@StringRes stringRes: String)

        fun goToLogin()
    }

    interface Presenter : BasePresenter {
        fun registerButtonClicked()
        fun LoginActionClicked()
    }
}