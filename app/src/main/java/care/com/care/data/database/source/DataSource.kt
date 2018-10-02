package care.com.care.data.database.source

import care.com.care.Model.Job
import care.com.care.Model.Token
import io.reactivex.Flowable

interface DataSource {
    fun getJobRequests(): Flowable<List<Job>>

    fun getJob(jobID : String)

    fun refreshJobs()

    fun applyForJob(jobID: String)

    fun submitJobRequest()

    fun deleteJob(jobID: String)

    fun getToken() : Flowable<Token>
}