package care.com.careOff.shift

import android.annotation.SuppressLint
import care.com.careOff.Model.Shift
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource

class ShiftDetailsPresenter(val shiftView: ShiftDetailsContract.View, val shiftId: Int, val sharedPref: SharedPref) : ShiftDetailsContract.Presenter {
    init {
        shiftView.setPresenter(this)
    }
    override fun unsubscribe() {
    }

    @SuppressLint("CheckResult")
    override fun subscribe() {
        val accessToken = sharedPref.fetch("x-access-token")
        val xID = sharedPref.fetch("x-id")
        RemoteDataSource.getShift(shiftId, accessToken, xID).subscribe(
                {
                    handleMessage(it.shift)
                }, {
                System.out.println(it.localizedMessage)
        }
        )
    }

    private fun handleMessage(shift: Shift) {
        System.out.println(shift)
    }

}