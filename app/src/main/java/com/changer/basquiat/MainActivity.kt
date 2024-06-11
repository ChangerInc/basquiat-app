package com.changer.basquiat

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.common.appModule
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.presentation.ui.navigate.BasquiatNavHost
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
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
            modules(appModule)
        }
        setContent {
            BasquiatTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val user by inject<UserPreferences>()
                    Log.d("MainActivity", "User: ${user.authToken.collectAsState(initial = null).value != null}")
                    val vmRegister by inject<RegisterViewModel>()
                    val vmLogin by inject<LoginViewModel>()
                    val vmHistoric by inject<HistoricoViewModel>()
                    val vmConversion by inject<ConversionViewModel>()
                    MainScreen(
                        vmLogin = vmLogin,
                        vmRegister = vmRegister,
                        vmHistoric = vmHistoric,
                        vmConversion = vmConversion,
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
    user: UserPreferences,
) {
    val navController = rememberNavController()
    val screens = listOf(Screen.Historic, Screen.Conversion)
    val authToken by user.authToken.collectAsState(initial = null)
    var initialScreen by remember { mutableStateOf<Screen?>(null) }
    var loading by remember { mutableStateOf(true) }

    LaunchedEffect(authToken) {
        initialScreen = if (authToken != null) Screen.Historic else Screen.Home
        loading = false
    }

    val currentScreen: MutableState<Screen> =
        remember { mutableStateOf(initialScreen ?: Screen.Home) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        val route = destination.route ?: return@addOnDestinationChangedListener
        val screen = screens.find { it.route == route }
        if (screen != null) currentScreen.value = screen
    }

    if (loading) {
        Log.d("MainScreen", "Loading...")
    } else if (initialScreen != null) {
        BasquiatNavHost(
            navController = navController,
            startDestination = currentScreen.value.route,
            vmLogin = vmLogin,
            vmHistoric = vmHistoric,
            vmRegister = vmRegister,
            vmConversion = vmConversion,
            user = user
        )
    }
}

//@Preview(showBackground = true)
//@Composable
//fun MainScreenPreview() {
//    BasquiatTheme {
//        MainScreen(vm)
////        com.changer.basquiat.presentation.ui.echo.EchoScreen()
//    }
//}