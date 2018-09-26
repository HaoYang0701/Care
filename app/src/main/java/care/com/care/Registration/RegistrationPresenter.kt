package care.com.care.Registration

class RegistrationPresenter(val registrationView : RegistrationContract.View) : RegistrationContract.Presenter{

    init {
        registrationView.setPresenter(this)
    }

    override fun unsubscribe() {

    }

    override fun subscribe() {
    }

}