package care.com.care.registration

import care.com.care.data.database.source.remote.RemoteDataSource
import org.json.JSONObject


class RegistrationPresenter(val registrationView : RegistrationContract.View) : RegistrationContract.Presenter{
    var observable: RegistrationObservable

    override fun registerButtonClicked() {
        val jsonParams = JSONObject()
        jsonParams.put("first_name", observable.firstName)
        jsonParams.put("last_name", observable.lastName)
        jsonParams.put("age", Integer.valueOf(observable.age))
        jsonParams.put("dob", "11-14-1997")
        jsonParams.put("cellphone_number", observable.phone)
        jsonParams.put("email", observable.email)
        jsonParams.put("password", observable.password)
        jsonParams.put("zip", Integer.valueOf(observable.zipCode))

        RemoteDataSource.registerNewUser(jsonParams.toString()).subscribe(
                {v ->
                    System.out.println(v.status) } ,
                {error ->
                    System.out.println(error.message)})
    }

    init {
        registrationView.setPresenter(this)
        observable = RegistrationObservable()
        registrationView.setObservable(observable)
    }

    override fun unsubscribe() {

    }

    override fun subscribe() {
    }

}