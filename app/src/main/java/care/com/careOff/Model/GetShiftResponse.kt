package care.com.careOff.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GetShiftResponse(
        @SerializedName("status")
        @Expose val status: Int,
        @SerializedName("success")
        @Expose val success: Boolean,
        @SerializedName("data")
        @Expose val shift: Shift,
        @SerializedName("message")
        @Expose val message: String
)