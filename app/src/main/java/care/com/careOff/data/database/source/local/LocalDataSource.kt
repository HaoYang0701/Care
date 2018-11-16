package care.com.careOff.data.database.source.local

import care.com.careOff.Model.*
import care.com.careOff.Network.DocumentUploadUrlRequest
import care.com.careOff.Network.LoginRequest
import care.com.careOff.Network.PushTokenUpdateRequest
import care.com.careOff.Network.RegistrationRequest
import care.com.careOff.Network.VerifyOTPRequest
import care.com.careOff.data.database.source.DataSource
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response

class LocalDataSource(val localDao : LocalDao) : DataSource {

    override fun logIn(body: LoginRequest): Observable<Response<LoginResponse>> {
        return Observable.empty()
    }

    override fun verifyOTP(body: VerifyOTPRequest, accessToken : String, xID : String): Observable<VerifyOTPResponse> {
        return Observable.empty()
    }

    override fun sendOTP(headermap : Map<String, String>): Observable<SendOTPResponse> {
        return Observable.empty()
    }

    override fun updateFirebaseToken(body: PushTokenUpdateRequest, accessToken : String, xID : String): Observable<PushTokenUpdateResponse> {
        return Observable.empty()
    }

    override fun uploadImage(body : DocumentUploadUrlRequest): Observable<DocumentUploadUrlResponse> {
        return Observable.empty()
    }

    override fun registerNewUser(request: RegistrationRequest): Observable<Response<RegistrationResponse>> {
        return Observable.empty()
    }

    override fun getToken(): Flowable<Token> {
        return Flowable.empty()
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

    override fun getJobRequests() : Flowable<List<Job>> {
        val jobs = localDao.getJobs()
        if (jobs.isNotEmpty()) {
            return Flowable.just(jobs)
        }
        return Flowable.empty()
    }

}