package me.nasiri.areca.peresentation.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.burnoo.cokoin.Koin
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import me.nasiri.areca.domain.di.appModule
import me.nasiri.areca.domain.util.TAGAPP
import me.nasiri.areca.peresentation.MainState
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
                    // OnBoarding(data = MainState().boardListData) {}
                    HomePage()
                }
            }
        }
    }
}
