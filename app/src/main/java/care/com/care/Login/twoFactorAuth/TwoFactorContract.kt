package care.com.care.Login.twoFactorAuth

import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

interface TwoFactorContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
    }
}