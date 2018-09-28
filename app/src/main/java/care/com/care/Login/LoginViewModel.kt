package care.com.care.Login

import androidx.lifecycle.ViewModel
import care.com.care.base.BaseViewModel

class LoginViewModel : BaseViewModel<LoginContract.Presenter>, ViewModel() {
    private var presenter : LoginContract.Presenter? = null

    override fun getPresenter(): LoginContract.Presenter? {
        return this.presenter
    }

    override fun setPresenter(presenter: LoginContract.Presenter) {
        if (this.presenter == null){
            this.presenter = presenter
        }
    }

    override fun onCleared() {
        super.onCleared()
        presenter = null
    }
}