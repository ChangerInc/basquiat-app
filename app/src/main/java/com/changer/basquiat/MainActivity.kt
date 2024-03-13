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
import com.changer.basquiat.ui.login.TopAppBarLoginCadastro
import com.changer.basquiat.ui.navigate.BasquiatNavHost
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
    BasquiatNavHost(startDestination = "home")
}

@Preview(showBackground = true)
@Composable
fun MainScreenPreview() {
    BasquiatTheme {
        MainScreen()
    }
}