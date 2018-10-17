package care.com.care.Network

import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import retrofit2.http.GET
import io.reactivex.Observable
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST


interface ApiEndpoint {
    @GET("/token")
    fun getToken() : Observable<Token>

    @Headers("Content-Type: application/json")
    @POST("/it/users/register")
    fun registerUser(@Body body : RegistrationRequest) : Observable<Response<RegistrationResponse>>
}