package care.com.careOff.data.database.source

import care.com.careOff.Model.*
import care.com.careOff.Network.*
import care.com.careOff.data.database.source.local.LocalDataSource
import care.com.careOff.data.database.source.remote.RemoteDataSource
import io.reactivex.Flowable
import io.reactivex.Observable
import retrofit2.Response

class Repository(val remoteDataSource : RemoteDataSource, val localDataSource: LocalDataSource) : DataSource {
    override fun applyShift(shiftInterestRequest: ShiftInterestRequest, accessToken: String, xID: String): Observable<ShiftInterestResponse> {
        return Observable.empty()
    }

    override fun getShift(shiftID: Int, accessToken: String, xID: String): Observable<GetShiftResponse> {
        return Observable.empty()
    }

    override fun getAllShifts(limit: Int, offset: Int, status: String, accessToken: String, xID: String): Observable<AllShiftResponse> {
        return Observable.empty()
    }

    override fun logIn(body: LoginRequest): Observable<Response<LoginResponse>> {
        return remoteDataSource.logIn(body)
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

    override fun registerNewUser(registrationRequest: RegistrationRequest): Observable<Response<RegistrationResponse>> {
        return remoteDataSource.registerNewUser(registrationRequest)
    }

    var casheIsDirty = false
    var cachedJobs : LinkedHashMap<String, Job> = LinkedHashMap()

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

    override fun getJobRequests() : Flowable<List<Job>>{
        if (cachedJobs.isNotEmpty() && !casheIsDirty){
            return Flowable.just(ArrayList<Job>(cachedJobs.values))
        }

        val remoteJob = getJobFromRemoteDataSource()

        if (casheIsDirty) {
            return remoteJob
        } else {
            val localJob = getAndCacheLocalJobs()
            return localJob
        }
    }
    
    private fun getJobFromRemoteDataSource(): Flowable<List<Job>> {
        return remoteDataSource.getJobRequests()
    }

    private fun getAndCacheLocalJobs(): Flowable<List<Job>> {
        return localDataSource.getJobRequests()
                .flatMap { jobs ->
                    Flowable.fromIterable(jobs)
                            .doOnNext { job -> cachedJobs.put(job.id.toString(), job)}
                            .toList()
                            .toFlowable()
                }
    }

}