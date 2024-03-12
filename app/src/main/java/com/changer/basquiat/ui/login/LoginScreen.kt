package com.changer.basquiat.ui.login

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.R
import com.changer.basquiat.ui.home.HomeScreen

@Preview
@Composable
fun LoginScreenPreview() {
    LoginScreen(navigateToHistorico = {}, navigateToCadastro = {})
}

@Composable
fun LoginScreen(
    navigateToHistorico: () -> Unit,
    navigateToCadastro: () -> Unit,
) {
    val email = remember { mutableStateOf("") }
    val senha = remember { mutableStateOf("") }
    val isEsqueciSenhaVisible = remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        topBar = {
            TopAppBarLayout(titulo = "Login", navigateToPage = { navController.navigate("home") })
        }
    ) { padding ->
        Column {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.White)
                    .padding(padding)
            ) {
                Column(
                    modifier = Modifier.align(Alignment.Center)
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.changer),
                        contentDescription = "Logo do aplicativo",
                        modifier = Modifier.size(100.dp)
                    )
                    Text(
                        text = "Entrar",
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(all = 16.dp)
                    )
                    TextField(
                        value = email.value,
                        onValueChange = { email.value = it },
                        label = { Text(text = "Email") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    TextField(
                        value = senha.value,
                        onValueChange = { senha.value = it },
                        label = { Text(text = "Senha") },
                        modifier = Modifier.fillMaxWidth()
                    )
                    Button(
                        onClick = navigateToHistorico,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Entrar")
                    }
                    TextButton(
                        onClick = { isEsqueciSenhaVisible.value = true },
                        modifier = Modifier.align(Alignment.End)
                    ) {
                        Text(text = "Esqueci minha senha")
                    }
                    if (isEsqueciSenhaVisible.value) {
                        EsqueciSenhaDialog(
                            onClose = { isEsqueciSenhaVisible.value = false }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EsqueciSenhaDialog(onClose: () -> Unit) {

}