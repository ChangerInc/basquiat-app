package com.changer.basquiat.ui.navigate

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.ui.home.HomeScreen
import com.changer.basquiat.ui.login.LoginScreen

@Composable
fun BasquiatNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController(),
    startDestination: String
) {
    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable("home") {
            HomeScreen(
                navigateToCadastro = { navController.navigate("cadastro") },
                navigateToLogin = { navController.navigate("login") },
                /*...*/
            )
        }
        composable("login") {
            LoginScreen(
                navigateToHistorico = { navController.navigate("historico") },
                navigateToHome = { navController.navigate("home") }
            ) }
    }
}