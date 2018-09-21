package care.com.care.Login

import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

interface LoginContract {

    interface View : BaseView<Presenter> {

    }

    interface Presenter : BasePresenter {

    }
}