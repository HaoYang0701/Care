package care.com.careOff.data.database.source

import care.com.careOff.Model.DocumentUploadUrlResponse
import care.com.careOff.Model.Job
import care.com.careOff.Model.RegistrationResponse
import care.com.careOff.Model.Token
import care.com.careOff.Network.DocumentUploadUrlRequest
import care.com.careOff.Network.RegistrationRequest
import io.reactivex.Flowable
import io.reactivex.Observable
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

    fun uploadImage(body : DocumentUploadUrlRequest) : Observable<DocumentUploadUrlResponse>
}