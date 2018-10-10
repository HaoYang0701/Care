package care.com.care.registration

import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

interface RegistrationContract {
    interface View : BaseView<Presenter> {
        fun setObservable(observable: RegistrationObservable)
    }

    interface Presenter : BasePresenter {
        fun registerButtonClicked()
    }
}