package care.com.care.data.database.source

import care.com.care.Model.Job
import care.com.care.Model.RegistrationResponse
import care.com.care.Model.Token
import io.reactivex.Flowable
import io.reactivex.Observable
import okhttp3.RequestBody

interface DataSource {
    fun getJobRequests(): Flowable<List<Job>>

    fun getJob(jobID : String)

    fun refreshJobs()

    fun applyForJob(jobID: String)

    fun submitJobRequest()

    fun deleteJob(jobID: String)

    fun getToken() : Flowable<Token>

    fun registerNewUser(jsonParams: String): Observable<RegistrationResponse>
}