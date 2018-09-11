package care.com.care

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.FrameLayout
import android.widget.RelativeLayout
import android.widget.Toast
import com.twilio.video.CameraCapturer
import com.twilio.jwt.accesstoken.AccessToken
import com.twilio.jwt.accesstoken.VideoGrant





class MainActivity : AppCompatActivity() {

    val ACCOUNT_SID = "ACCOUNT_SID"
    val API_KEY_SID = "API_KEY_SID"
    val API_KEY_SECRET = "API_KEY_SECRET"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val previewFrameLayout = findViewById<FrameLayout>(R.id.frame)
        val localContainer = findViewById<RelativeLayout>(R.id.local_container)
        val peerContainer = findViewById<RelativeLayout>(R.id.peer_container)
    }
}
