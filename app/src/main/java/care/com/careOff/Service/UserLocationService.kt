package care.com.careOff.Service

import android.app.*
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.os.Build
import android.os.Bundle
import android.os.IBinder
import androidx.core.app.NotificationCompat
import care.com.careOff.R
import care.com.careOff.registration.RegistrationActivity
import com.google.android.gms.common.ConnectionResult
import com.google.android.gms.common.api.GoogleApiClient
import com.google.android.gms.location.*

class UserLocationService : Service(), GoogleApiClient.ConnectionCallbacks,
        GoogleApiClient.OnConnectionFailedListener, LocationListener {
    lateinit var fusedLocationClient : FusedLocationProviderClient

    override fun onProviderDisabled(p0: String?) {

    }

    override fun onProviderEnabled(p0: String?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onStatusChanged(p0: String?, p1: Int, p2: Bundle?) {

    }

    override fun onLocationChanged(p0: Location?) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionFailed(p0: ConnectionResult) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnectionSuspended(p0: Int) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onConnected(p0: Bundle?) {
        val locationRequest = LocationRequest()
        locationRequest.priority = LocationRequest.PRIORITY_HIGH_ACCURACY
        locationRequest.interval = 5000
        locationRequest.fastestInterval = 5000

        val locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                locationResult ?: return
                for (location in locationResult.locations){
                    System.out.println(location.longitude)
                }
            }
        }
        fusedLocationClient.requestLocationUpdates(locationRequest, locationCallback, null)
    }

    override fun onBind(p0: Intent?): IBinder? {
        return null
    }

    private val NOTIFICATION_CHANNEL_ID = "notification_channel_id"
    private val NOTIFICATION_Service_CHANNEL_ID = "service_channel"



    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)
        val googleApiClient = GoogleApiClient.Builder(this)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(this)
                .addOnConnectionFailedListener(this).build()

        googleApiClient.connect()

        var icon = R.mipmap.ic_launcher

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            icon = R.mipmap.ic_launcher
        }

        val notificationIntent = Intent(this, RegistrationActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this, 0, notificationIntent, 0)
        val builder = NotificationCompat.Builder(this)
                .setSmallIcon(icon)
                .setContentIntent(pendingIntent)
                .setContentTitle("Service")
                .setContentText("Running...")
        var notification = builder.build()
        if (Build.VERSION.SDK_INT >= 26) {
            val channel = NotificationChannel(NOTIFICATION_Service_CHANNEL_ID, "Sync Service", NotificationManager.IMPORTANCE_HIGH)
            channel.description = "Service Name"
            val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)

            notification = Notification.Builder(this, NOTIFICATION_Service_CHANNEL_ID)
                    .setContentTitle("Service")
                    .setContentText("Running...")
                    .setSmallIcon(icon)
                    .setContentIntent(pendingIntent)
                    .build()
        }
        startForeground(121, notification)
        return START_NOT_STICKY
    }
}