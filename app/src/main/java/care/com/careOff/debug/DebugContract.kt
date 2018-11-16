package care.com.careOff.debug

import care.com.careOff.Model.ServerType
import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView

class DebugContract {

    interface Presenter : BasePresenter {
        fun setServerPreference(serverType : ServerType)
    }

    interface View : BaseView<Presenter> {
        fun setDebugServerText(serverType: ServerType)
    }

}