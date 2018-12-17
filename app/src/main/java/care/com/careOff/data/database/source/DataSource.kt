package care.com.careOff.data.database.source

import care.com.careOff.Model.*
import care.com.careOff.Network.*
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

    fun logIn(body : LoginRequest) : Observable<Response<LoginResponse>>

    fun updateFirebaseToken(body : PushTokenUpdateRequest, accessToken : String, xID : String) : Observable<PushTokenUpdateResponse>

    fun sendOTP(headermap : Map<String, String>) : Observable<SendOTPResponse>

    fun verifyOTP(body : VerifyOTPRequest, accessToken : String, xID : String) : Observable<VerifyOTPResponse>

    fun getAllShifts(limit : Int, offset : Int, status : String, accessToken : String, xID : String) : Observable<AllShiftResponse>

    fun getShift(shiftID : Int, accessToken : String, xID : String) : Observable<GetShiftResponse>

    fun applyShift(shiftInterestRequest: ShiftInterestRequest, accessToken : String, xID : String) : Observable<ShiftInterestResponse>

    fun getUserDetails(ccessToken : String, xID : String) : Observable<UserDetailResponse>
}