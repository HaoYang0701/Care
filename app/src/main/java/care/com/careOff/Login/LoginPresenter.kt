package care.com.careOff.login

import android.annotation.SuppressLint
import care.com.careOff.Network.LoginRequest
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource

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
                    val accessToken = response.headers().get("x-access-token")
                    val xID = response.headers().get("x-id")
                    if (accessToken != null) {
                        sharedPref.update("x-access-token", accessToken)
                    }
                    if (xID != null) {
                        sharedPref.update("x-id", xID)
                    }

                    loginView.goToHomeScreen()
                },

                { error ->

                })
    }
}
