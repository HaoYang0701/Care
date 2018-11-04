package care.com.careOff.documentupload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import care.com.careOff.Model.DocumentUploadUrlResponse
import care.com.careOff.Network.DocumentUploadUrlRequest
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import care.com.careOff.data.database.source.remote.RemoteDataSource
import com.google.gson.GsonBuilder
import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import okhttp3.*
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.http.*


class DocumentUploadPresenter(val documentUploadView: DocumentUploadContract.View, val externalFilesDir: File) : DocumentUploadContract.Presenter {
    lateinit var mCurrentPhotoPath: String

    override fun createFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = externalFilesDir
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpeg", /* suffix */
                storageDir /* directory */
        ).apply {
            // Save a file: path for use with ACTION_VIEW intents
            mCurrentPhotoPath = absolutePath
        }
    }

    override fun uploadFile() {
        val targetW = 1024
        val targetH = 720

        val bmOptions = BitmapFactory.Options().apply {
            // Get the dimensions of the bitmap
            inJustDecodeBounds = true
            BitmapFactory.decodeFile(mCurrentPhotoPath, this)
            val photoW: Int = outWidth
            val photoH: Int = outHeight

            // Determine how much to scale down the image
            val scaleFactor: Int = Math.min(photoW / targetW, photoH / targetH)

            // Decode the image file into a Bitmap sized to fill the View
            inJustDecodeBounds = false
            inSampleSize = scaleFactor
        }
        BitmapFactory.decodeFile(mCurrentPhotoPath, bmOptions)?.also { image ->

            val file = File(externalFilesDir,  SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()) + ".jpeg")
            file.createNewFile()
            val bitmap = image
            documentUploadView.setLicenseImage(bitmap)
            Observable.just(bitmap).flatMap {
                it ->
                val bos = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 100 /*ignored for PNG*/, bos)
                val bitmapdata = bos.toByteArray()

                val fos = FileOutputStream(file)
                fos.write(bitmapdata)
                fos.flush()
                fos.close()
                Observable.just(file)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(object : Observer<File> {
                override fun onError(e: Throwable) {
                    val a = e.message
                    System.out.println(a)
                }

                override fun onNext(t: File) {
                    getUploadDocumentUrl(t)
                }

                override fun onSubscribe(d: Disposable) {
                    System.out.println(d.isDisposed)
                }

                override fun onComplete() {
                    System.out.println("HOW")
                }

            })
        }
    }

    fun getUploadDocumentUrl(t: File) {
        val reqFile = RequestBody.create(MediaType.parse("image/jpeg"), t)
        val body = MultipartBody.Part.createFormData("upload", t.name, reqFile)
        val documentUploadUrlRequest = DocumentUploadUrlRequest()
        documentUploadUrlRequest.userName = "H12"
        documentUploadUrlRequest.documentType = "license"

        RemoteDataSource.uploadImage(documentUploadUrlRequest).subscribe(object : Observer<DocumentUploadUrlResponse> {
            override fun onSubscribe(d: Disposable) {
                System.out.println(d.isDisposed)
            }

            override fun onError(e: Throwable) {

                val a = e.message
                System.out.println(a)
            }

            override fun onNext(t: DocumentUploadUrlResponse) {
                val clientService = createApiServiceFromUrl()
                uploadImages(clientService, t.URL, body)
            }

            override fun onComplete() {

            }

        })
    }

    private fun uploadImages(service : ClientService?, url: String, body: MultipartBody.Part) {
        service?.getClientList(url, body)?.subscribeOn(Schedulers.io())?.observeOn(AndroidSchedulers.mainThread())?.subscribe(object : Observer<ResponseBody> {
            override fun onError(e: Throwable) {
                val mes = e.message
                System.out.println(mes)
            }

            override fun onNext(t: ResponseBody) {
                val r = t.string()
                System.out.println(r)
            }

            override fun onSubscribe(d: Disposable) {
                System.out.println(d.isDisposed)
            }

            override fun onComplete() {

            }

        })
    }

    interface ClientService {
        @Multipart
        @PUT
        fun getClientList(@Url url: String, @Part image: MultipartBody.Part): Observable<ResponseBody>
    }

    private fun createApiServiceFromUrl(): ClientService? {
        val interceptor = HttpLoggingInterceptor()
        interceptor.level = HttpLoggingInterceptor.Level.BODY
        val gson = GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create()


        val client = OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .addInterceptor(interceptor)
                .build()

        val retrofit = Retrofit.Builder()
                .baseUrl("http://myhost/mypath/")
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .client(client)
                .build()

        val service = retrofit.create(ClientService::class.java)
        return service
    }

    init {
        documentUploadView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}