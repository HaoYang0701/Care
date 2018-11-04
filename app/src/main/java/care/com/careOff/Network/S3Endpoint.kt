package care.com.careOff.Network

import care.com.careOff.Model.DocumentUploadUrlResponse
import io.reactivex.Observable
import okhttp3.MultipartBody
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part

interface S3Endpoint {
    @Multipart
    @POST("/it/document/documentuploadurl")
    fun postImage(@Part image: MultipartBody.Part, @Part("Body")body : DocumentUploadUrlRequest): Observable<DocumentUploadUrlResponse>
}