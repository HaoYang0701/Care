package care.com.careOff.documentupload

import android.graphics.Bitmap
import care.com.careOff.base.BasePresenter
import care.com.careOff.base.BaseView
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