package care.com.careOff.registration

import androidx.lifecycle.ViewModel
import care.com.careOff.base.BaseViewModel

class RegistrationViewModel : BaseViewModel<RegistrationContract.Presenter>, ViewModel() {
    private var presenter : RegistrationContract.Presenter? = null

    override fun getPresenter(): RegistrationContract.Presenter? {
        return this.presenter
    }

    override fun setPresenter(presenter: RegistrationContract.Presenter) {
        if (this.presenter == null){
            this.presenter = presenter
        }
    }

    override fun onCleared() {
        super.onCleared()
        presenter = null
    }
}