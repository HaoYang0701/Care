package care.com.care.Login

class LoginPresenter(val loginView: LoginContract.View) : LoginContract.Presenter {

    init {
        loginView.setPresenter(this)
    }


    override fun subscribe() {

    }

    override fun unsubscribe() {

    }
}