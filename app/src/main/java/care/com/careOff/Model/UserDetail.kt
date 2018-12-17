package care.com.careOff.Model

import com.google.gson.annotations.SerializedName

data class UserDetail (
        @SerializedName("first_name")
        val firstName : String,
        @SerializedName("last_name")
        val lastName : String,
        @SerializedName("user_account_status")
        val accountStatus : String,
        @SerializedName("background_check_status")
        val backgroundCheckStatus : String
)