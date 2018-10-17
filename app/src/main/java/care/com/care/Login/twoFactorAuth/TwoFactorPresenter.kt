package care.com.care.Login.twoFactorAuth

class TwoFactorPresenter(val twoFactorView: TwoFactorContract.View) : TwoFactorContract.Presenter {

    init {
        twoFactorView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}