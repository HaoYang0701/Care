package care.com.careOff.login.twoFactorAuth

import care.com.careOff.Model.SendOTPResponse
import care.com.careOff.Network.VerifyOTPRequest
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class TwoFactorPresenter(val twoFactorView: TwoFactorContract.View, val sharedPref: SharedPref) : TwoFactorContract.Presenter {

    lateinit var accessToken : String
    lateinit var xID : String

    init {
        accessToken = sharedPref.fetch("x-access-token")
        xID = sharedPref.fetch("x-id")
        twoFactorView.setPresenter(this)
        resendOTP()
    }

    override fun verifyOTP(otpPin : String) {
        val otpRequest = VerifyOTPRequest()
        otpRequest.otp = otpPin
        RemoteDataSource.verifyOTP(otpRequest, accessToken, xID).subscribe{
            twoFactorView.goToHomeScreen()
        }
    }

    override fun resendOTP() {
        val headermap = LinkedHashMap<String, String>()
        headermap.put("x-access-token", accessToken)
        headermap.put("x-id", xID)

        RemoteDataSource.sendOTP(headermap).subscribe(object : Observer<SendOTPResponse>{
            override fun onError(e: Throwable) {
                System.out.println(e.message)
            }

            override fun onNext(t: SendOTPResponse) {
                System.out.println(t.status)
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println("fdsgfdg")
            }

            override fun onComplete() {
                System.out.println("fdsgfdg")
            }

        })
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}