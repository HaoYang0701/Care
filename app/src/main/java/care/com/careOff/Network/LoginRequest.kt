package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class LoginRequest {
    @SerializedName("cellphone_number")
    @Expose
    var phone: String = ""


    @SerializedName("password")
    @Expose
    var password: String = ""
}