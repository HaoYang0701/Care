package care.com.careOff.passwordreset

class PasswordResetPresenter(val passwordResetView: PasswordResetContract.View) : PasswordResetContract.Presenter {
    override fun onCancelClicked() {
        passwordResetView.goToWelcomeScreen()
    }

    override fun onResetButtonClicked() {

    }

    init {
        passwordResetView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}