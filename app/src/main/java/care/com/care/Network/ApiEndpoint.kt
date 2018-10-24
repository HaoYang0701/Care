package care.com.care.Network

import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import io.reactivex.Observable
import retrofit2.Response
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.http.*


interface ApiEndpoint {
    @GET("/token")
    fun getToken() : Observable<Token>

    @Headers("Content-Type: application/json")
    @POST("/it/users/register")
    fun registerUser(@Body body : RegistrationRequest) : Observable<Response<RegistrationResponse>>

    @Multipart
    @POST("/yourEndPoint")
    fun postImage(@Part image: MultipartBody.Part): Observable<ResponseBody>
}