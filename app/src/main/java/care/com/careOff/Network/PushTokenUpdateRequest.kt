package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class PushTokenUpdateRequest (
        @SerializedName("firebase_token")
        @Expose
        var firebaseToken: String = ""
)