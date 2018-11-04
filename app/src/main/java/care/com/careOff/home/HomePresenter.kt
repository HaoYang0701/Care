package care.com.careOff.home

class HomePresenter(val homeView: HomeContract.View) : HomeContract.Presenter {

    init {
        homeView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}