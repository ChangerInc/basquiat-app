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
import com.changer.basquiat.ui.login.presentation.LoginViewModel
import com.changer.basquiat.ui.navigate.BasquiatNavHost
import com.changer.basquiat.ui.theme.BasquiatTheme
import org.koin.android.ext.android.inject
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startKoin {
            androidContext(this@MainActivity)
            modules(appModule)
        }
        setContent {
            BasquiatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val vm by inject<LoginViewModel>()
                    MainScreen(vm)
                }
            }
        }
    }
}

@Composable
fun MainScreen(vm: LoginViewModel) {
    BasquiatNavHost(startDestination = "home", vm = vm)
}


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    BasquiatTheme {
//        MainScreen(vm)
////        EchoScreen()
//    }
//}