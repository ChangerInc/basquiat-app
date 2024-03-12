package com.changer.basquiat

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.ui.home.HomeScreen
import com.changer.basquiat.ui.login.LoginScreen
import com.changer.basquiat.ui.theme.BasquiatTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BasquiatTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainScreen()
                }
            }
        }
    }
}

@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController, startDestination = "home") {
        composable("home") { HomeScreen(navigateToLogin = { navController.navigate("login") }, navigateToCadastro = { navController.navigate("cadastro") }) }
        composable("login") { LoginScreen(navigateToHistorico = { navController.navigate("historico") }, navigateToCadastro = { navController.navigate("cadastro") }) }
        composable("cadastro") { CadastroScreen(navigateToLogin = { navController.navigate("login") }) }
        composable("historico") { HistoricoScreen() }

        composable("circulos") { CirculoScreen() }
        composable("conversao") { ConversaoScreen() }
    }

}

@Composable
fun ConversaoScreen() {
    TODO("Not yet implemented")
}

@Composable
fun CirculoScreen() {
    TODO("Not yet implemented")
}

@Composable
fun HistoricoScreen() {
    TODO("Not yet implemented")
}

@Composable
fun CadastroScreen(navigateToLogin: () -> Unit) {
    Text(
        text = "TELA DE CADASTRO"
    )
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BasquiatTheme {
        MainScreen()
    }
}