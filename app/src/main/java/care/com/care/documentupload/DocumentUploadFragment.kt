package care.com.care.documentupload

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.FileProvider
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import care.com.care.R
import care.com.care.Utils.PermissionUtil
import care.com.care.databinding.DocumentUploadFragmentBinding

class DocumentUploadFragment : DocumentUploadContract.View, Fragment() {

    override fun setLicenseImage(bitmap: Bitmap) {
        viewBinding.licenseImage.setImageBitmap(bitmap)
    }

    private lateinit var presenter: DocumentUploadContract.Presenter
    private lateinit var viewBinding: DocumentUploadFragmentBinding
    val REQUEST_LICENSE_CAPTURE = 1

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewBinding = DataBindingUtil.inflate(inflater, R.layout.document_upload_fragment, container, false)
        val view = viewBinding.root
        setUpClickListeners()
        return view
    }

    private fun setUpClickListeners() {
        viewBinding.uploadLicense.setOnClickListener { view ->
            Intent(MediaStore.ACTION_IMAGE_CAPTURE).also { takePictureIntent ->
                if (PermissionUtil.isCameraPermissionsOn(context!!)) {
                } else if (!PermissionUtil.isCameraPermissionsOn(context!!)) {
                    PermissionUtil.checkCameraPermissions(context!!)
                }
                takePictureIntent.resolveActivity(context?.packageManager)?.also {
                    val photoFile = presenter.createFile()

                    photoFile?.also {
                        val photoURI = FileProvider.getUriForFile(
                                context!!,
                                "com.care.android.fileprovider",
                                it
                        )
                        takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                        startActivityForResult(takePictureIntent, REQUEST_LICENSE_CAPTURE)
                    }
                }
            }
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_LICENSE_CAPTURE && resultCode == RESULT_OK) {
            presenter.uploadFile()
        }
    }


    override fun onResume() {
        super.onResume()
        presenter.subscribe()
    }

    override fun onPause() {
        super.onPause()
        presenter.unsubscribe()
    }

    override fun setPresenter(presenter: DocumentUploadContract.Presenter) {
        this.presenter = presenter
    }


    companion object {
        fun newInstance() : DocumentUploadFragment {
            return DocumentUploadFragment()
        }
    }
}