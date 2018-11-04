package care.com.careOff.Login

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {

    override fun onFabClicked() {
        loginView.goToSettings()
    }

    init {
        loginView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }

    override fun onRegistrationClicked() {
        loginView.goToRegistrationScreen()
    }

    override fun onLoginButtonClicked() {
    }
}