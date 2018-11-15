package care.com.careOff.login.welcome

import androidx.lifecycle.ViewModel
import care.com.careOff.base.BaseViewModel

class WelcomeViewModel : BaseViewModel<WelcomeContract.Presenter>, ViewModel() {
    private var presenter : WelcomeContract.Presenter? = null


    override fun getPresenter(): WelcomeContract.Presenter? {
        return this.presenter
    }

    override fun setPresenter(presenter: WelcomeContract.Presenter) {
        if (this.presenter == null){
            this.presenter = presenter
        }
    }

    override fun onCleared() {
        super.onCleared()
        presenter = null
    }
}