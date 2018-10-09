package care.com.care.base

interface BaseViewModel<T> {

    fun setPresenter(presenter : T)

    fun getPresenter() : T?
}