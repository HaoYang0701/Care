package care.com.careOff.Network

import care.com.careOff.Model.DocumentUploadUrlResponse
import care.com.careOff.Model.LoginResponse
import care.com.careOff.Model.RegistrationResponse
import care.com.careOff.Model.Token
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

    @POST("/it/users/Login")
    fun logIn(@Body body : LoginRequest) : Observable<Response<LoginResponse>>

    @POST("/it/users/firebasetoken")
    fun sendNewDeviceToken(@Body body : PushTokenUpdateRequest, @Header("x-access-token") accessToken : String,
                           @Header("x-user-id") xID : String) : Observable<PushTokenUpdateResponse>

    @POST("/it/users/sendPhoneOtp")
    fun sendOTP(@HeaderMap headerMap: Map<String, String>) : Observable<SendOTPResponse>

    //@Header("x-id") xID : String, @Header("x-access-token"
    @POST("/it/users/verifyPhoneOtp")
    fun verifyOTP(@Body body : VerifyOTPRequest, @Header("x-access-token") accessToken : String,
                  @Header("x-id") xID : String) : Observable<VerifyOTPResponse>

    @POST("/shift/user/interest")
    fun respondShiftInterest(@Body body : ShiftInterestRequest, @Header("x-access-token") accessToken : String,
                             @Header("x-id") xID : String) : Observable<ShiftInterestResponse>

    @POST("it/shift/{id}")
    fun getShift(@Path("id") id : Int, @Header("x-access-token") accessToken : String,
                 @Header("x-id") xID : String) : Observable<GetShiftResponse>
}