package care.com.careOff.shift

import android.annotation.SuppressLint
import care.com.careOff.Model.Shift
import care.com.careOff.Utils.DateUtil
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
        val accessToken = "5ffa5cb70c09e6a0a2c21f1bba5211220c32e3e19a82571579cddda57fedb389" //sharedPref.fetch("x-access-token")
        val xID = "46" //sharedPref.fetch("x-id")
        RemoteDataSource.getShift(shiftId, accessToken, xID).subscribe(
                {
                    handleMessage(it.shift)
                }, {
                System.out.println(it.localizedMessage)
        })
    }

    private fun handleMessage(shift: Shift) {
        shiftView.setDate(DateUtil.getDate(shift.startDate))
        shiftView.setDistance("0.3 Miles Away")
        shiftView.setTime(DateUtil.getTime(shift.startDate, shift.lengthInMins))
    }

}