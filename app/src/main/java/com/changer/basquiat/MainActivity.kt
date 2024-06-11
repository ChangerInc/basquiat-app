package com.changer.basquiat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.common.appModule
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.firebaseModule
import com.changer.basquiat.presentation.ui.navigate.BasquiatNavHost
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.viewmodel.CircleViewModel
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
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
            modules(
                appModule,
                firebaseModule
            )
        }

        setContent {
            BasquiatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user by inject<UserPreferences>()
                    val vmRegister by inject<RegisterViewModel>()
                    val vmLogin by inject<LoginViewModel>()
                    val vmHistoric by inject<HistoricoViewModel>()
                    val vmConversion by inject<ConversionViewModel>()
                    val vmCircle by inject<CircleViewModel>()
                    MainScreen(
                        vmLogin = vmLogin,
                        vmRegister = vmRegister,
                        vmHistoric = vmHistoric,
                        vmConversion = vmConversion,
                        vmCircle = vmCircle,
                        user = user
                    )
                }
            }
        }
    }
}

@Composable
fun MainScreen(
    vmLogin: LoginViewModel,
    vmRegister: RegisterViewModel,
    vmHistoric: HistoricoViewModel,
    vmConversion: ConversionViewModel,
    vmCircle: CircleViewModel,
    user: UserPreferences,
) {
    val navController = rememberNavController()
    val screens = listOf(Screen.Historic, Screen.Conversion)

    val currentScreen: MutableState<Screen> =
        remember { mutableStateOf(Screen.Home) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        val route = destination.route ?: return@addOnDestinationChangedListener
        val screen = screens.find { it.route == route }
        if (screen != null) currentScreen.value = screen
    }

    BasquiatNavHost(
        navController = navController,
        startDestination = currentScreen.value.route,
        vmLogin = vmLogin,
        vmHistoric = vmHistoric,
        vmRegister = vmRegister,
        vmConversion = vmConversion,
        vmCircle = vmCircle,
        user = user
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