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
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import com.changer.basquiat.presentation.viewmodel.LoginViewModel
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
                    val vmLogin by inject<LoginViewModel>()
                    val vmHistoric by inject<HistoricoViewModel>()
                    MainScreen(vmLogin, vmHistoric)
                }
            }
        }
    }
}

@Composable
fun MainScreen(vmLogin: LoginViewModel, vmHistoric: HistoricoViewModel) {
    BasquiatNavHost(
        startDestination = "home",
        vmLogin = vmLogin,
        vmHistoric = vmHistoric
    )
}


//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    BasquiatTheme {
//        MainScreen(vm)
////        com.changer.basquiat.presentation.ui.echo.EchoScreen()
//    }
//}