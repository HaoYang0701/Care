package care.com.care.data.database.source

import care.com.care.Model.Job
import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import care.com.care.Network.RegistrationRequest
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody
import retrofit2.Response

interface DataSource {
    fun getJobRequests(): Flowable<List<Job>>

    fun getJob(jobID : String)

    fun refreshJobs()

    fun applyForJob(jobID: String)

    fun submitJobRequest()

    fun deleteJob(jobID: String)

    fun getToken() : Flowable<Token>

    fun registerNewUser(request: RegistrationRequest): Observable<Response<RegistrationResponse>>
}