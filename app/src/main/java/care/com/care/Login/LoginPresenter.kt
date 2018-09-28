package care.com.care.Login

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {

    init {
        loginView.setPresenter(this)
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {

    }

    override fun onForgotPasswordClicked() {

    }

    override fun onRegistrationClicked() {
        loginView.goToRegistrationScreen()
    }

    override fun onLoginButtonClicked() {
        TODO(reason = "not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}