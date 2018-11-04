package care.com.careOff.registration

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import care.com.careOff.BR

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
    var dayOfBirth : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.dayOfBirth)
        }

    @get:Bindable
    var yearOfBirth : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.yearOfBirth)
        }


    @get:Bindable
    var monthOfBirth : Int = 0
        set(value) {
            field = value
            notifyPropertyChanged(BR.monthOfBirth)
        }

    @get:Bindable
    var showRegistrationError : Boolean = false
        set(value) {
            field = value
            notifyPropertyChanged(BR.showRegistrationError)
        }
}