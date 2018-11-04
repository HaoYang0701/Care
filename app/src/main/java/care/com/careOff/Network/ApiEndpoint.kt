package care.com.careOff.Network

import care.com.careOff.Model.DocumentUploadUrlResponse
import care.com.careOff.Model.RegistrationResponse
import care.com.careOff.Model.Token
import io.reactivex.Observable
import retrofit2.Response
import okhttp3.MultipartBody
import retrofit2.http.*


interface ApiEndpoint {
    @GET("/token")
    fun getToken() : Observable<Token>

    @Headers("Content-Type: application/json")
    @POST("/it/users/register")
    fun registerUser(@Body body : RegistrationRequest) : Observable<Response<RegistrationResponse>>

    @Multipart
    @POST("/it/document/documentuploadurl")
    fun postImage(@Part image: MultipartBody.Part, @Part("Body")body : DocumentUploadUrlRequest): Observable<DocumentUploadUrlResponse>

    @POST("/it/document/documentuploadurl")
    fun getImageURL(@Body body : DocumentUploadUrlRequest): Observable<DocumentUploadUrlResponse>

}