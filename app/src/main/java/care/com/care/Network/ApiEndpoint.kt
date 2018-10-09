package care.com.care.Network

import care.com.care.Model.Token
import retrofit2.Retrofit
import retrofit2.http.GET
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory


interface ApiEndpoint {
    @GET("/token")
    fun getToken() : Observable<Token>
}