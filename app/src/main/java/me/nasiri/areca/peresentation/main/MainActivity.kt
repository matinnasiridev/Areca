package me.nasiri.areca.peresentation.main

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.burnoo.cokoin.Koin
import me.nasiri.areca.domain.di.appModule
import me.nasiri.areca.peresentation.ui.OnBoarding
import me.nasiri.areca.peresentation.ui.theme.ArecaTheme
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        setContent {
            Koin(appDeclaration = {
                androidContext(this@MainActivity)
                modules(appModule)
            }) {
                ArecaTheme {
                    OnBoarding(data = MainState().boardListData) {

                    }
                }
            }
        }
    }
}
