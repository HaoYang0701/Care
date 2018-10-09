package care.com.care.registration

import androidx.databinding.ObservableField

class RegistrationPresenter(val registrationView : RegistrationContract.View) : RegistrationContract.Presenter{
    val email = ObservableField<String>()

    fun getEmail(): String? {
        return this.email.get()
    }

    override fun registerButtonClicked() {
        System.out.println(email.get())
    }

    init {
        registrationView.setPresenter(this)
    }

    override fun unsubscribe() {

    }

    override fun subscribe() {
    }

}