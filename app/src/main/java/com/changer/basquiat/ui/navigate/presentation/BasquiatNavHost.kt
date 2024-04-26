package com.changer.basquiat.ui.navigate.presentation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.ui.home.presentation.HomeScreen
import com.changer.basquiat.ui.login.presentation.LoginScreen
import com.changer.basquiat.ui.register.presentation.RegisterScreen

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
                navigateToRegister = { navController.navigate("register") },
                navigateToLogin = { navController.navigate("login") },
                /*...*/
            )
        }
        composable("login") {
            LoginScreen(
                navigateToHistorico = { navController.navigate("historico") },
                navigateToHome = { navController.navigate("home") }
            ) }
        composable("register") {
            RegisterScreen(
                navigateToLogin = { navController.navigate("login") },
                navigateToHome = { navController.navigate("home") }
            ) }
    }
}