package care.com.careOff.Model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class UserDetailResponse (
        @SerializedName("status")
        @Expose val status : String,
        @SerializedName("success")
        @Expose val success : Boolean,
        @SerializedName("data")
        @Expose val userDetail : UserDetail,
        @SerializedName("message")
        @Expose val message : String
)