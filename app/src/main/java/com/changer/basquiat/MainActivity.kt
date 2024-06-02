package com.changer.basquiat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.changer.basquiat.common.appModule
import com.changer.basquiat.presentation.ui.navigate.BasquiatNavHost
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.viewmodel.LoginViewModel
import com.changer.basquiat.presentation.viewmodel.RegisterViewModel
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
                    val loginVm by inject<LoginViewModel>()
                    val registerVm by inject<RegisterViewModel>()
                    MainScreen(loginVm, registerVm)
                }
            }
        }
    }
}

@Composable
fun MainScreen(loginVm: LoginViewModel, registerVm: RegisterViewModel) {
    BasquiatNavHost(
        startDestination = "home",
        loginVm = loginVm,
        registerVm = registerVm
    )
}


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    BasquiatTheme {
//        MainScreen(vm)
////        EchoScreen()
//    }
//}