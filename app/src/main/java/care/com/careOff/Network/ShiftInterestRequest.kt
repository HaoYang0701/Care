package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class ShiftInterestRequest {
    @SerializedName("shift_id")
    @Expose
    var shiftID : Int = 0
}