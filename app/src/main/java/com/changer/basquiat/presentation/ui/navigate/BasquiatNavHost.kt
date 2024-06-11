package com.changer.basquiat.presentation.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.presentation.ui.circle.CircleScreen
import com.changer.basquiat.presentation.ui.conversion.ConversionScreen
import com.changer.basquiat.presentation.ui.historic.HistoricScreen
import com.changer.basquiat.presentation.ui.home.HomeScreen
import com.changer.basquiat.presentation.ui.login.LoginScreen
import com.changer.basquiat.presentation.ui.register.RegisterScreen
import com.changer.basquiat.presentation.viewmodel.CircleViewModel
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import com.changer.basquiat.presentation.viewmodel.LoginViewModel
import com.changer.basquiat.presentation.viewmodel.RegisterViewModel
import kotlinx.coroutines.launch

@Composable
fun BasquiatNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String,
    vmLogin: LoginViewModel,
    vmHistoric: HistoricoViewModel,
    vmRegister: RegisterViewModel,
    vmConversion: ConversionViewModel,
    vmCircle: CircleViewModel,
    user: UserPreferences
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(Screen.Home.route) {
            val isAuthenticated by vmLogin.isAuthenticated.collectAsState(initial = false)
            LaunchedEffect(isAuthenticated) {
                if (isAuthenticated) {
                    navController.navigate(Screen.Historic.route)
                }
            }
            HomeScreen(
                navigateToRegister = { navController.navigate(Screen.Register.route) },
                navigateToLogin = { navController.navigate(Screen.Login.route) }
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
            val scope = rememberCoroutineScope()
            HistoricScreen(
                navController = navController,
                vm = vmHistoric,
                signOut = {
                    scope.launch {
                        user.clear()
                        vmHistoric.signOut()
                        navController.navigate(Screen.Home.route)
                    }
                }
            )
        }

        composable(Screen.Conversion.route) {
            val scope = rememberCoroutineScope()
            ConversionScreen(
                navController = navController,
                vm = vmConversion,
                signOut = {
                    scope.launch {
                        user.clear()
                        vmConversion.signOut()
                        navController.navigate(Screen.Home.route)
                    }
                }
            )
        }

        composable(Screen.Circles.route) {
            val scope = rememberCoroutineScope()
            CircleScreen(
                navController = navController,
                vm = vmCircle,
                signOut = {
                    scope.launch {
                        user.clear()
                        vmCircle.signOut()
                        navController.navigate(Screen.Home.route)
                    }
                }
            )
        }
    }
}