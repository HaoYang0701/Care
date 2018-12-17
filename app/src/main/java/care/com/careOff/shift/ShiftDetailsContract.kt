package care.com.careOff.shift

import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

class ShiftDetailsContract {

    interface Presenter : BasePresenter {

    }

    interface View : BaseView<Presenter> {
        fun setDate(string : String)
        fun setDistance(string : String)
        fun setTime(string : String)
    }
}