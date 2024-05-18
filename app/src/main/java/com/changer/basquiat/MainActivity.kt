package com.changer.basquiat

import EchoScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.common.appModule
import com.changer.basquiat.ui.home.presentation.HomeScreen
import com.changer.basquiat.ui.login.presentation.LoginScreen
import com.changer.basquiat.ui.navigate.BasquiatNavHost
import com.changer.basquiat.ui.theme.BasquiatTheme
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasquiatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
            startKoin {
                androidContext(this@MainActivity)
                modules(appModule)
            }
        }
    }
}

@Composable
fun MainScreen() {
    BasquiatNavHost(startDestination = "home")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BasquiatTheme {
      MainScreen()

    }
}