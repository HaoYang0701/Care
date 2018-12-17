package care.com.careOff.data.database.source.remote

import care.com.careOff.Network.ApiEndpoint
import care.com.careOff.Network.RegistrationRequest
import care.com.careOff.data.database.source.DataSource
import com.google.gson.GsonBuilder
import io.reactivex.BackpressureStrategy
import io.reactivex.Flowable
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.Interceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import care.com.careOff.Model.*
import care.com.careOff.Network.DocumentUploadUrlRequest
import care.com.careOff.Network.LoginRequest
import care.com.careOff.Network.*


object RemoteDataSource : DataSource {
    override fun getUserDetails(accessToken: String, xID: String): Observable<UserDetailResponse> {
        return apiEndpoint.getUserDetails(accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun applyShift(shiftInterestRequest: ShiftInterestRequest, accessToken: String, xID: String): Observable<ShiftInterestResponse> {
        return apiEndpoint.respondShiftInterest(shiftInterestRequest, accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getShift(shiftID: Int, accessToken: String, xID: String): Observable<GetShiftResponse> {
        return apiEndpoint.getShift(shiftID, accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun getAllShifts(limit: Int, offset: Int, status: String, accessToken: String, xID: String): Observable<AllShiftResponse> {
        return apiEndpoint.getListOfShifts(limit, offset, status, accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }


    override fun logIn(body: LoginRequest): Observable<Response<LoginResponse>> {
        return apiEndpoint.logIn(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun verifyOTP(body: VerifyOTPRequest, accessToken : String, xID : String): Observable<VerifyOTPResponse> {
        return apiEndpoint.verifyOTP(body, accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun sendOTP(headermap : Map<String, String>): Observable<SendOTPResponse> {
        return apiEndpoint.sendOTP(headermap).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun updateFirebaseToken(body: PushTokenUpdateRequest, accessToken : String, xID : String): Observable<PushTokenUpdateResponse> {
        return apiEndpoint.sendNewDeviceToken(body, accessToken, xID).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }

    override fun uploadImage(body : DocumentUploadUrlRequest) : Observable<DocumentUploadUrlResponse>{
        return apiEndpoint.getImageURL(body).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }



    private var apiEndpoint: ApiEndpoint

    init {
        val BASE_URL = "https://oix65g6mn2.execute-api.us-east-1.amazonaws.com"
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()

        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY

        var uniqueID = ""

//        val sPrefs = PreferenceManager.getDefaultSharedPreferences()
//        if (sPrefs.getString("key_uuid", null) != null) {
//            uniqueID = sPrefs.getString("key_uuid", null)
//        } else {
//            uniqueID = UUID.randomUUID().toString()
//            val editor = sPrefs.edit()
//            editor.putString("key_uuid", uniqueID)
//            editor.commit()
//        }


        val deviceInfo = ""//getDeviceInfo()

        val headerInterceptor = Interceptor { chain ->
            val request = chain.request().newBuilder()
                    .addHeader("DeviceInfo", deviceInfo)
                    .addHeader("ClientID", uniqueID).build()
            chain.proceed(request)
        }
        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(headerInterceptor)
                .build()

        val  retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()
        apiEndpoint = retrofit.create(ApiEndpoint::class.java)
    }

//    private fun getDeviceInfo(): String {
//        val telephonyManager = CareOffApplication.getAppContext()?.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
//        val simOperatorName = telephonyManager.simOperatorName
//
//        val stringBuilder = StringBuilder()
//        stringBuilder.apply {
//            append(Build.BRAND)
//            append(" ")
//            append(Build.MODEL)
//            append(" ")
//            append(Build.VERSION.SDK_INT)
//            append(" ")
//            append(simOperatorName)
//        }
//        return stringBuilder.toString()
//    }


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


    override fun registerNewUser(registrationRequest : RegistrationRequest): Observable<Response<RegistrationResponse>> {
        return apiEndpoint.registerUser(registrationRequest).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
    }


    override fun getJobRequests() : Flowable<List<Job>>{
        return Flowable.empty()
    }
}