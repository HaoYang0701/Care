package care.com.careOff.shift

class ShiftPresenter(val shiftView : ShiftContract.View) : ShiftContract.Presenter {

    init {
        shiftView.setPresenter(this)
    }
    override fun unsubscribe() {
    }

    override fun subscribe() {
    }

}