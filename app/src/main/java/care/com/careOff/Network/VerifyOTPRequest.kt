package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class VerifyOTPRequest(
        @SerializedName("otp")
        @Expose
        var otp : String = ""
)