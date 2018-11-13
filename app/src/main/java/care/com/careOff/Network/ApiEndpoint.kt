package care.com.careOff.Network

import care.com.careOff.Model.*
import io.reactivex.Observable
import retrofit2.Response
import okhttp3.MultipartBody
import retrofit2.http.*
import retrofit2.http.Body


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

    @POST("/it/users/firebasetoken")
    fun sendNewDeviceToken(@Body body : PushTokenUpdateRequest, @Header("x-access-token") accessToken : String,
                           @Header("x-id") xID : String) : Observable<PushTokenUpdateResponse>

    @POST("it/users/sendPhoneOtp")
    fun sendOTP(@Header("x-access-token") accessToken : String,
                @Header("x-id") xID : String) : Observable<SendOTPResponse>

    @POST("it/users/verifyPhoneOtp")
    fun verifyOTP(@Body body : VerifyOTPRequest, @Header("x-access-token") accessToken : String,
                  @Header("x-id") xID : String) : Observable<VerifyOTPResponse>
}