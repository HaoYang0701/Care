package care.com.careOff.base

interface BaseViewModel<T> {

    fun setPresenter(presenter : T)

    fun getPresenter() : T?
}