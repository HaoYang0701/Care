package care.com.careOff.login

import android.annotation.SuppressLint
import care.com.careOff.Model.PushTokenUpdateResponse
import care.com.careOff.Network.LoginRequest
import care.com.careOff.Network.PushTokenUpdateRequest
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import io.reactivex.Observer
import io.reactivex.disposables.Disposable

class LoginPresenter(val loginView: LoginContract.View, val sharedPref: SharedPref) : LoginContract.Presenter {

    var loginObservable : LoginObservable

    init {
        loginView.setPresenter(this)
        loginObservable = LoginObservable()
        loginView.setObservable(loginObservable)
    }
    override fun onForgotPasswordClicked() {
        loginView.goToForgotPasswordScreen()
    }

    override fun onCancelClicked() {
        loginView.goToWelcomeScreen()
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }

    @SuppressLint("CheckResult")
    override fun onLoginButtonClicked() {
        val loginRequest = LoginRequest()
        loginRequest.phone = loginObservable.phone
        loginRequest.password = loginObservable.password

        RemoteDataSource.logIn(loginRequest).subscribe(
                { response ->
                    if (response.isSuccessful) {
                        val accessToken = response.headers().get("x-access-token")
                        val xID = response.headers().get("x-id")
                        if (accessToken != null) {
                            sharedPref.update("x-access-token", accessToken)
                        }
                        if (xID != null) {
                            sharedPref.update("x-id", xID)
                        }
                        updateFirebaseTokens()
                    } else {
                        loginView.showLoginError()
                    }
                },

                { error ->
                    loginView.showLoginError()
                })
    }

    private fun updateFirebaseTokens() {
        val accessToken = sharedPref.fetch("x-access-token")
        val xID = sharedPref.fetch("x-id")
        val tokenRequest = PushTokenUpdateRequest(sharedPref.fetch("deviceToken"))

        RemoteDataSource.updateFirebaseToken(tokenRequest, accessToken, xID).subscribe(object : Observer<PushTokenUpdateResponse> {
            override fun onError(e: Throwable) {
                System.out.println("Firebase" + e.message)
            }

            override fun onNext(t: PushTokenUpdateResponse) {
                System.out.println("SUCCESS FIRE" + t.success)
                loginView.goToHomeScreen()
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println(d.isDisposed)
            }

            override fun onComplete() {
            }
        })
    }
}
