package care.com.care.documentupload

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.File
import java.text.SimpleDateFormat
import java.util.*
import care.com.care.data.database.source.remote.RemoteDataSource
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import okhttp3.MediaType
import java.io.ByteArrayOutputStream
import java.io.FileOutputStream
import okhttp3.MultipartBody
import okhttp3.RequestBody




class DocumentUploadPresenter(val documentUploadView: DocumentUploadContract.View, val externalFilesDir: File) : DocumentUploadContract.Presenter {
    lateinit var mCurrentPhotoPath: String

    override fun createFile(): File? {
        val timeStamp: String = SimpleDateFormat("yyyyMMdd_HHmmss").format(Date())
        val storageDir: File = externalFilesDir
        return File.createTempFile(
                "JPEG_${timeStamp}_", /* prefix */
                ".jpg", /* suffix */
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

            val file = File(externalFilesDir,  SimpleDateFormat("yyyyMMdd_HHmmss").format(Date()))
            file.createNewFile()
            val bitmap = image
            documentUploadView.setLicenseImage(bitmap)
            Observable.just(bitmap).flatMap {
                it ->
                val bos = ByteArrayOutputStream()
                it.compress(Bitmap.CompressFormat.JPEG, 0 /*ignored for PNG*/, bos)
                val bitmapdata = bos.toByteArray()

                val fos = FileOutputStream(file)
                fos.write(bitmapdata)
                fos.flush()
                fos.close()
                Observable.just(file)
            }.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe{it ->
                val reqFile = RequestBody.create(MediaType.parse("image/*"), it)
                val body = MultipartBody.Part.createFormData("upload", file.name, reqFile)
                RemoteDataSource.uploadImage(body)
            }
        }
    }

    init {
        documentUploadView.setPresenter(this)
    }

    override fun subscribe() {
    }

    override fun unsubscribe() {

    }
}