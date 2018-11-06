package care.com.careOff.Login

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {
    override fun onForgotPasswordClicked() {
        loginView.goToForgotPasswordScreen()
    }

    override fun onCancelClicked() {
        loginView.goToWelcomeScreen()
    }

    init {
        loginView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }

    override fun onLoginButtonClicked() {
    }
}