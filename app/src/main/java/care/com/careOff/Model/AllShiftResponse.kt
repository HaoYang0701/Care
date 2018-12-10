package care.com.careOff.Model

import com.google.gson.annotations.SerializedName

data class AllShiftResponse (
        @SerializedName("count") val count: Int = 0,
        @SerializedName("rows") val shiftList : ArrayList<ShiftDetail>
)