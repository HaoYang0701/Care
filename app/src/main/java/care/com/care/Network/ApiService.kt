package care.com.care.Network

import care.com.care.Model.Token
import retrofit2.Retrofit
import retrofit2.http.GET
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import retrofit2.converter.gson.GsonConverterFactory


interface ApiService {

    companion object {
        const val BASE_URL = "http://care-216403.appspot.com"

        val gson = GsonBuilder()
        .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
        .create()

        fun createRetrofitService() : ApiService {
            val  retrofit = Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                    .build()
            return retrofit.create(ApiService::class.java)
        }
    }

    @GET("/token")
    fun getToken() : Observable<Token>
}