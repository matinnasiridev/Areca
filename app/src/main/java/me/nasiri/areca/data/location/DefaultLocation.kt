package me.nasiri.areca.data.location


import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationManager
import androidx.core.content.ContextCompat
import com.google.android.gms.location.FusedLocationProviderClient
import kotlinx.coroutines.suspendCancellableCoroutine
import me.nasiri.areca.domain.location.LocationClient
import kotlin.coroutines.resume

class DefaultLocation(
    private val locationClient: FusedLocationProviderClient,
    private val context: Context
) : LocationClient {

    override suspend fun getCurrentLocation(): Location? {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val isGpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER) ||
                locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER)

        val check: Context.() -> Boolean = {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED && ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        }

        if (!check(context) || !isGpsEnabled)
            return null

        return suspendCancellableCoroutine { con ->
            locationClient.lastLocation.apply {
                if (isComplete) {
                    if (isSuccessful) {
                        con.resume(result)
                    } else {
                        con.resume(null)
                    }
                    return@suspendCancellableCoroutine
                }
                addOnSuccessListener {
                    con.resume(it)
                }
                addOnFailureListener {
                    con.resume(null)
                }
                addOnCanceledListener {
                    con.cancel()
                }
            }

        }
    }
}