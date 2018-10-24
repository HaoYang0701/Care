package care.com.care.documentupload

import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import care.com.care.R
import care.com.care.Utils.replaceFragmentInActivity
import care.com.care.data.database.source.remote.RemoteDataSource

class DocumentUploadActivity : AppCompatActivity(){

    private lateinit var documentUploadPresenter: DocumentUploadPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.document_upload_activity)

        val documentUploadFragment = supportFragmentManager.findFragmentById(R.id.contentFrame)
                as DocumentUploadFragment? ?: DocumentUploadFragment.newInstance().also {
            replaceFragmentInActivity(it, R.id.contentFrame)
        }

        documentUploadPresenter = DocumentUploadPresenter(documentUploadFragment, getExternalFilesDir(Environment.DIRECTORY_PICTURES))

        documentUploadFragment.setPresenter(documentUploadPresenter)
    }
}