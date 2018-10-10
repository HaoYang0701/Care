package care.com.care.registration

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import care.com.care.BR

class RegistrationObservable : BaseObservable() {

    @get:Bindable
    var email: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.email)
        }

    @get:Bindable
    var firstName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.firstName)
        }


    @get:Bindable
    var lastName: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.lastName)
        }


    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }

    @get:Bindable
    var confirmPassword: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.confirmPassword)
        }

    @get:Bindable
    var zipCode: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.zipCode)
        }

    @get:Bindable
    var phone: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }


    @get:Bindable
    var age: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.age)
        }

    @get:Bindable
    var dateOfBirth : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.dateOfBirth)
        }
}