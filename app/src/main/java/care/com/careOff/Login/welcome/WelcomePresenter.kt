package care.com.careOff.login.welcome

class WelcomePresenter(val welcomeView: WelcomeContract.View) : WelcomeContract.Presenter {

    override fun onFabClicked() {
        welcomeView.goToSettings()
    }

    init {
        welcomeView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }

    override fun onRegistrationClicked() {
        welcomeView.goToRegistrationScreen()
    }

    override fun onLoginButtonClicked() {
        welcomeView.goToSignIn()
    }
}