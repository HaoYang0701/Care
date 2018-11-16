package care.com.careOff.registration

import android.annotation.SuppressLint
import android.location.Geocoder
import care.com.careOff.Network.RegistrationRequest
import care.com.careOff.Utils.SharedPref
import care.com.careOff.data.database.source.remote.RemoteDataSource
import org.threeten.bp.LocalDate
import org.threeten.bp.ZoneId
import org.threeten.bp.temporal.ChronoUnit
import java.io.IOException


class RegistrationPresenter(val registrationView: RegistrationContract.View, val sharedPref: SharedPref, val geocoder: Geocoder) : RegistrationContract.Presenter{
    var observable: RegistrationObservable

    init {
        registrationView.setPresenter(this)
        observable = RegistrationObservable()
        registrationView.setObservable(observable)
    }

    override fun LoginActionClicked() {
        registrationView.goToLogin()
    }



    @SuppressLint("CheckResult")
    override fun registerButtonClicked() {

        val registrationRequest = RegistrationRequest()
        registrationRequest.firstName = observable.firstName
        registrationRequest.lastName = observable.lastName
        registrationRequest.cellphoneNumber = observable.phone
        registrationRequest.email = observable.email
        registrationRequest.password = observable.password
        registrationRequest.zip = observable.zipCode

        if (!observable.password.equals(observable.confirmPassword)) {
            registrationView.setConfirmPasswordError()
            return
        }

        if (!isInputValid(registrationRequest)) {
            return
        }

        val date = createDate(observable)
        if (date == null) {
            registrationView.setDobError()
            return
        }
        registrationRequest.dob = date


        try {
            val addresses = geocoder.getFromLocationName(registrationRequest.zip, 1)
            if (addresses != null && !addresses.isEmpty()) {
                val address = addresses[0]
                // Use the address as needed
                registrationRequest.latitude = address.latitude.toString()
                registrationRequest.longitude = address.longitude.toString()
            }
        } catch (e: IOException) {
            // handle exception
        }



        RemoteDataSource.registerNewUser(registrationRequest).subscribe(
                    { response ->
                        if (response.isSuccessful) {
                            val accessToken = response.headers().get("x-access-token")
                            val xID = response.headers().get("x-id")
                            if (accessToken != null) {
                                sharedPref.update("x-access-token", accessToken)
                            }
                            if (xID != null) {
                                sharedPref.update("x-id", xID)
                            }
                            registrationView.goToHomeScreen()
                        } } ,
                    { error ->
                        observable.showRegistrationError = true
                        registrationView.showToast(error.localizedMessage)
                    })
    }

    private fun createDate(observable: RegistrationObservable): String? {
        val day = observable.dayOfBirth
        val month = observable.monthOfBirth
        val year = observable.yearOfBirth

        if (day == 0 || month == 0 || year == 0) {
            return null
        }

        val start = LocalDate.of( year , month + 1 , day ) ;
        val stop = LocalDate.now( ZoneId.of( "America/Montreal" ) );
        val years = ChronoUnit.YEARS.between( start , stop )

        if (years < 18) {
            return null
        }

        val stringBuilder = StringBuilder()

        stringBuilder.apply {
            append(month + 1)
            append("-")
            append(day)
            append("-")
            append(year)
        }
        return stringBuilder.toString()
    }

    private fun isInputValid(registrationRequest: RegistrationRequest): Boolean {
        var isValid = true

        if (registrationRequest.cellphoneNumber.length < 10) {
            registrationView.setPhoneNumberError()
            isValid = false
        }
        if (registrationRequest.firstName.isBlank()) {
            isValid = false
            registrationView.setFirstNameError()
        }

        if (registrationRequest.lastName.isBlank()) {
            isValid = false
            registrationView.setLastNameError()
        }
        if (registrationRequest.password.isBlank() || registrationRequest.password.length < 8) {
            isValid = false
            registrationView.setPasswordError()
        }

        if (!registrationRequest.email.contains("@") || !registrationRequest.email.contains(".com")) {
            isValid = false
            registrationView.setEmailError()
        }

        if (registrationRequest.zip.length != 5) {
            isValid = false
            registrationView.setZipCodeError()
        }
        return isValid
    }


    override fun unsubscribe() {

    }

    override fun subscribe() {
    }

}