package care.com.careOff.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class AllShiftResponse(
        @SerializedName("status")
        @Expose val status : Int,
        @SerializedName("success")
        @Expose val  success : Boolean,
        @SerializedName("data")
        @Expose val data : ShiftData,
        @SerializedName("message")
        @Expose val message : String
)

data class ShiftData(
        @SerializedName("count")
        @Expose val count : Int,
        @SerializedName("rows")
        @Expose val shiftDetailList : List<ShiftDetail>? = null
)