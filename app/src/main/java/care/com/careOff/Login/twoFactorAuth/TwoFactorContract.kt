package care.com.careOff.Login.twoFactorAuth

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface TwoFactorContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
    }
}