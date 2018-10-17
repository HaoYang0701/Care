package care.com.care.registration

import androidx.annotation.StringRes
import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

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
    }

    interface Presenter : BasePresenter {
        fun registerButtonClicked()
    }
}