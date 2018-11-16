package care.com.careOff.debug

import care.com.careOff.Model.ServerType
import care.com.careOff.Utils.SharedPref

class DebugPresenter(val debugView: DebugContract.View, val sharedPref: SharedPref) : DebugContract.Presenter {
    override fun setServerPreference(serverType: ServerType) {
        sharedPref.update("serverType", serverType.name)
        debugView.setDebugServerText(serverType)
    }

    init {
        debugView.setPresenter(this)
    }

    override fun unsubscribe() {
}

    override fun subscribe() {
    }
}