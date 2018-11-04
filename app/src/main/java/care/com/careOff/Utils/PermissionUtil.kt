package care.com.careOff.Utils

import care.com.careOff.R
import android.Manifest
import androidx.core.app.ActivityCompat
import android.widget.Toast
import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.content.ContextCompat


class PermissionUtil {
    companion object {
        private val MY_PERMISSIONS_REQUEST_FINE_LOCATIONS = 1
        private val MY_PERMISSIONS_REQUEST_CAMERA = 2

        fun isLocationPermissionsOn(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
        }

        fun checkLocationPermissions(context: Context) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                PermissionPopup(context)
            } else {
                val text = context.getString(R.string.location_already_enabled)
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }

        fun PermissionPopup(context: Context) {
            ActivityCompat.requestPermissions(context as Activity,
                    arrayOf(Manifest.permission.ACCESS_FINE_LOCATION),
                    MY_PERMISSIONS_REQUEST_FINE_LOCATIONS)
        }


        fun isCameraPermissionsOn(context: Context): Boolean {
            return ContextCompat.checkSelfPermission(context, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED
        }

        fun checkCameraPermissions(context: Context) {
            if (ActivityCompat.checkSelfPermission(context, android.Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
                cameraPermissionPopup(context)
            } else {
                val text = context.getString(R.string.location_already_enabled)
                val duration = Toast.LENGTH_LONG
                val toast = Toast.makeText(context, text, duration)
                toast.show()
            }
        }

        fun cameraPermissionPopup(context: Context) {
            ActivityCompat.requestPermissions(context as Activity,
                    arrayOf(Manifest.permission.CAMERA),
                    MY_PERMISSIONS_REQUEST_FINE_LOCATIONS)
        }
    }
}