package me.nasiri.areca.domain.di

import android.content.Context
import com.google.android.gms.location.LocationServices
import me.nasiri.areca.data.location.DefaultLocation
import me.nasiri.areca.domain.location.LocationClient
import org.koin.android.ext.koin.androidContext
import me.nasiri.areca.domain.util.SharedName
import me.nasiri.areca.peresentation.MainVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single { androidContext().getSharedPreferences(SharedName, Context.MODE_PRIVATE) }

    single<LocationClient> {
        DefaultLocation(
            LocationServices.getFusedLocationProviderClient(
                androidContext()
            ), androidContext()
        )
    }

    viewModel { MainVM() }
}