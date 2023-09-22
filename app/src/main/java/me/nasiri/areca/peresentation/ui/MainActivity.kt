package me.nasiri.areca.peresentation.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dev.burnoo.cokoin.Koin
import me.nasiri.areca.domain.di.appModule
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
                    ArecaApp(/*state = MainState()*/)
                }
            }
        }
    }
}

@Composable
fun ArecaApp() {
    // OnBoarding(data = state.boardListData) {}
    // MainView(state)
    Surface(modifier = Modifier.padding(12.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(5) {
                Spacer(modifier = Modifier.height(12.dp))
                ItemGrid()
                Spacer(modifier = Modifier.height(12.dp))
                ItemList()
            }
        }
    }
}