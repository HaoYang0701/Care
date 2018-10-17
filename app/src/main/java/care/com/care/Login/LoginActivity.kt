package care.com.care.Login

import android.annotation.TargetApi
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import care.com.care.R
import care.com.care.Utils.PermissionUtil
import care.com.care.Utils.replaceFragmentInActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import android.os.Build



class LoginActivity : AppCompatActivity(){

    private lateinit var loginPresenter: LoginPresenter
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_activity)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val loginViewModel = ViewModelProviders.of(this).get(LoginViewModel::class.java)

        val loginFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as LoginFragment? ?: LoginFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }


        loginPresenter = loginViewModel.getPresenter() as LoginPresenter? ?:
                LoginPresenter(loginFragment).also {
                    loginViewModel.setPresenter(it)
                }


        loginFragment.setPresenter(loginPresenter)


        if (PermissionUtil.isLocationPermissionsOn(this)) {
        } else if (!PermissionUtil.isLocationPermissionsOn(this)) {
            PermissionUtil.checkLocationPermissions(this)
        }

//        val myService = Intent(this, UserLocationService::class.java)
//        startForegroundService(myService)
    }

    private fun checkPlayServices(): Boolean {
        val apiAvailability = GoogleApiAvailability.getInstance()
        val resultCode = apiAvailability.isGooglePlayServicesAvailable(this)

        if (resultCode != ConnectionResult.SUCCESS) {
            if (apiAvailability.isUserResolvableError(resultCode)) {
                apiAvailability.getErrorDialog(this, resultCode, 1)
            } else {
                finish()
            }
            return false
        }
        return true
    }
}