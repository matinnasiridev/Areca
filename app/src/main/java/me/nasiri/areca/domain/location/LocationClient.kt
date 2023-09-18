package me.nasiri.areca.domain.location

import android.location.Location


interface LocationClient {
    suspend fun getCurrentLocation(): Location?
}