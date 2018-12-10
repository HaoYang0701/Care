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


        fun clearFirstNameError()

        fun clearLastNameError()

        fun clearEmailError()

        fun clearZipCodeError()

        fun clearPhoneNumberError()

        fun clearDobError()

        fun clearPasswordError()

        fun clearConfirmPasswordError()

        fun setObservable(observable: RegistrationObservable)

        fun showToast(@StringRes stringRes: String)

        fun goToLogin()
    }

    interface Presenter : BasePresenter {
        fun registerButtonClicked()
        fun LoginActionClicked()

        fun checkValidFirstName()
        fun checkValidLastName()
        fun checkValidZip()
        fun checkValidPhone()
        fun checkValidPassword()
        fun checkValidEmail()
    }
}