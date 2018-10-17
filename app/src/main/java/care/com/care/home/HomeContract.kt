package care.com.care.home

import care.com.care.base.BasePresenter
import care.com.care.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
    }
}