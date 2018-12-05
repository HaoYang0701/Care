package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName


class RegistrationRequest {

    @SerializedName("first_name")
    @Expose
    var firstName: String = ""
    @SerializedName("last_name")
    @Expose
    var lastName: String = ""
    @SerializedName("dob")
    @Expose
    var dob: String = ""
    @SerializedName("cellphone_number")
    @Expose
    var cellphoneNumber: String = ""
    @SerializedName("email")
    @Expose
    var email: String = ""
    @SerializedName("password")
    @Expose
    var password: String = ""
    @SerializedName("postal_code")
    @Expose
    var zip: String = ""

    @SerializedName("latitude")
    @Expose
    var latitude: Double = 0.0
    @SerializedName("longitude")
    @Expose
    var longitude: Double = 0.0

}