package me.nasiri.areca.domain.di

import android.content.Context
import org.koin.android.ext.koin.androidContext
import me.nasiri.areca.domain.util.SharedName
import org.koin.dsl.module

val appModule = module {

    single { androidContext().getSharedPreferences(SharedName, Context.MODE_PRIVATE) }


}