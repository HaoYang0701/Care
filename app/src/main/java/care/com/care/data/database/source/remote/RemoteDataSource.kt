package care.com.care.data.database.source.remote

import care.com.care.Model.Job
import care.com.care.Model.Token
import care.com.care.Network.ApiEndpoint
import care.com.care.data.database.source.DataSource
import com.google.gson.GsonBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

object RemoteDataSource : DataSource {
    private var apiEndpoint: ApiEndpoint

    init {
        val BASE_URL = "http://care-216403.appspot.com"
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

        val  retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        apiEndpoint = retrofit.create(ApiEndpoint::class.java)
    }


    override fun deleteJob(jobID: String) {
    }

    override fun submitJobRequest() {
    }

    override fun applyForJob(jobID: String) {

    }

    override fun refreshJobs() {

    }

    override fun getJob(jobID: String) {

    }

    override fun getToken(): Flowable<Token> {
        return apiEndpoint.getToken().toFlowable(BackpressureStrategy.BUFFER).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
    }

    override fun getJobRequests() : Flowable<List<Job>>{
        return Flowable.empty()
    }
}