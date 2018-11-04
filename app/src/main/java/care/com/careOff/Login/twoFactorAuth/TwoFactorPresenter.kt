package care.com.careOff.Login.twoFactorAuth

class TwoFactorPresenter(val twoFactorView: TwoFactorContract.View) : TwoFactorContract.Presenter {

    init {
        twoFactorView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}