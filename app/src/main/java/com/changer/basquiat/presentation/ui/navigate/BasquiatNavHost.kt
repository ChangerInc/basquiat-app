package com.changer.basquiat.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.presentation.ui.historic.HistoricScreen
import com.changer.basquiat.presentation.ui.home.HomeScreen
import com.changer.basquiat.presentation.ui.login.LoginScreen
import com.changer.basquiat.presentation.ui.register.RegisterScreen
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import com.changer.basquiat.presentation.viewmodel.LoginViewModel

@Composable
fun BasquiatNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String,
    user: UserPreferences,
    vmLogin: LoginViewModel,
    vmHistoric: HistoricoViewModel,
) {

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(
                navigateToRegister = { navController.navigate("register") },
                navigateToLogin = { navController.navigate("login") },
                /*...*/
            )
        }

        composable("login") {
            LoginScreen(
                navigateToHistorico = { navController.navigate("historico") },
                navigateToHome = { navController.navigate("home") },
                vm = vmLogin
            )
        }

        composable("register") {
            RegisterScreen(
                navigateToLogin = { navController.navigate("login") },
                navigateToHome = { navController.navigate("home") }
            )
        }

        composable("historico") {
            HistoricScreen(
                navigationToHistoric = { navController.navigate("historico") },
                navigationToConversion = { navController.navigate("conversion") },
                navigationToCircles = { navController.navigate("circles") },
                vm = vmHistoric,
                user
            )
        }
    }
}