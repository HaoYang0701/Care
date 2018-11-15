package care.com.careOff.login

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable
import care.com.careOff.BR

class LoginObservable : BaseObservable() {

    @get:Bindable
    var phone: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.phone)
        }

    @get:Bindable
    var password: String = ""
        set(value) {
            field = value
            notifyPropertyChanged(BR.password)
        }
}