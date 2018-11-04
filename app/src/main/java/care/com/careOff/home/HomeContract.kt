package care.com.careOff.home

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

interface HomeContract {

    interface View : BaseView<Presenter>

    interface Presenter : BasePresenter {
    }
}