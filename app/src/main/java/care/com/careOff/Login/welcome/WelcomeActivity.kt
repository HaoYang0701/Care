package care.com.careOff.login.welcome

import android.annotation.TargetApi
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import care.com.careOff.R
import care.com.careOff.Utils.PermissionUtil
import care.com.careOff.Utils.replaceFragmentInActivity
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.GoogleApiAvailability
import android.os.Build
import care.com.CareOffAppCompatActivity
import care.com.careOff.Utils.SharedPref
import care.com.careOff.home.HomeActivity


class WelcomeActivity : CareOffAppCompatActivity(){

    private lateinit var welcomePresenter: WelcomePresenter
    private var mFirebaseAnalytics: FirebaseAnalytics? = null

    @TargetApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val sharedPref = SharedPref(this)
        val accessToken = sharedPref.fetch("x-access-token")
        val xID = sharedPref.fetch("x-id")
        if (!accessToken.isEmpty() && !xID.isEmpty()) {
            goToHome()
        }

        setContentView(R.layout.welcome_activity)
        mFirebaseAnalytics = FirebaseAnalytics.getInstance(this)

        val welcomeViewModel = ViewModelProviders.of(this).get(WelcomeViewModel::class.java)

        val welcomeFragment  = supportFragmentManager.findFragmentById(R.id.contentFrame)
                        as WelcomeFragment? ?: WelcomeFragment.newInstance().also {
                    replaceFragmentInActivity(it, R.id.contentFrame)
                }


        welcomePresenter = welcomeViewModel.getPresenter() as WelcomePresenter? ?:
                WelcomePresenter(welcomeFragment).also {
                    welcomeViewModel.setPresenter(it)
                }

        welcomeFragment.setPresenter(welcomePresenter)


        if (PermissionUtil.isLocationPermissionsOn(this)) {
        } else if (!PermissionUtil.isLocationPermissionsOn(this)) {
            PermissionUtil.checkLocationPermissions(this)
        }

//        val myService = Intent(this, UserLocationService::class.java)
//        startForegroundService(myService)
    }

    private fun goToHome() {
        val intent = Intent(this, HomeActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        startActivity(intent)
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