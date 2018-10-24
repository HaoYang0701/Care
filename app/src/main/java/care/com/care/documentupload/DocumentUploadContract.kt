package care.com.care.documentupload

import android.graphics.Bitmap
import care.com.care.base.BasePresenter
import care.com.care.base.BaseView
import java.io.File

interface DocumentUploadContract {

    interface View : BaseView<Presenter> {
        fun setLicenseImage(bitmap : Bitmap)
    }

    interface Presenter : BasePresenter {
        fun createFile(): File?

        fun uploadFile()
    }
}