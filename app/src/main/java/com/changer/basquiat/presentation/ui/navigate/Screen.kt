package com.changer.basquiat.presentation.ui.navigate

sealed class Screen(val route: String) {

    data object Home : Screen("home")
    data object Login : Screen("login")
    data object Register : Screen("register")
    data object Historic : Screen("historic")
    data object Conversion : Screen("conversion")
    data object Circles : Screen("circles")
}