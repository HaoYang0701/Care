package care.com.careOff.Network

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class DocumentUploadUrlRequest {
    @SerializedName("userid")
    @Expose
    var userName: String = ""
    @SerializedName("documenttype")
    @Expose
    var documentType: String = ""
}