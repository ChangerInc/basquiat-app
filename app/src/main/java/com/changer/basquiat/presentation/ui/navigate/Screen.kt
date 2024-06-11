package com.changer.basquiat.presentation.ui.navigate

sealed class Screen(val route: String) {

    object Home : Screen("home")
    object Login : Screen("login")
    object Register : Screen("register")
    object Historic : Screen("historic")
    object Conversion : Screen("conversion")
    object Circles : Screen("circles")
}