package care.com.care.Model

import com.google.gson.annotations.SerializedName

data class Token(
        @SerializedName("id") val id: Int = 0,
        @SerializedName("content") val content: String = ""
)