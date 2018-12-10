package care.com.careOff.Model

import com.google.gson.annotations.SerializedName

class GetShiftResponse (
        val status: Int,
        val success: Boolean,
        @SerializedName("data") val shift : Shift,
        val meesage : String
)