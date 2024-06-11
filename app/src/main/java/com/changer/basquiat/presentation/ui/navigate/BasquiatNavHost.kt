package com.changer.basquiat.presentation.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.presentation.ui.conversion.ConversionScreen
import com.changer.basquiat.presentation.ui.historic.HistoricScreen
import com.changer.basquiat.presentation.ui.home.HomeScreen
import com.changer.basquiat.presentation.ui.login.LoginScreen
import com.changer.basquiat.presentation.ui.register.RegisterScreen
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import com.changer.basquiat.presentation.viewmodel.LoginViewModel
import com.changer.basquiat.presentation.viewmodel.RegisterViewModel

@Composable
fun BasquiatNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    vmLogin: LoginViewModel,
    vmHistoric: HistoricoViewModel,
    vmRegister: RegisterViewModel,
    vmConversion: ConversionViewModel,
    user: UserPreferences
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            HomeScreen(
                navigateToRegister = { navController.navigate(Screen.Register.route) },
                navigateToLogin = { navController.navigate(Screen.Login.route) },
                /*...*/
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navigateToHistorico = { navController.navigate(Screen.Historic.route) },
                navigateToHome = { navController.navigate(Screen.Home.route) },
                vm = vmLogin
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                navigateToLogin = { navController.navigate(Screen.Login.route) },
                navigateToHome = { navController.navigate(Screen.Home.route) },
                vm = vmRegister
            )
        }

        composable(Screen.Historic.route) {
            HistoricScreen(
                navController = navController,
                vm = vmHistoric
            )
        }

        composable(Screen.Conversion.route) {
            ConversionScreen(
                navController = navController,
                vm = vmConversion
            )
        }
    }
}