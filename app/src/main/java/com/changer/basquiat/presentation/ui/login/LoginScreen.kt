package com.changer.basquiat.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.R
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.presentation.viewmodel.LoginViewModel
import com.changer.basquiat.presentation.ui.components.InputEmail
import com.changer.basquiat.presentation.ui.components.InputPassword
import com.changer.basquiat.presentation.ui.components.Loading
import com.changer.basquiat.presentation.ui.components.TopAppBarLoginCadastro
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Preto


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHistorico: () -> Unit,
    navigateToHome: () -> Unit,
    vm: LoginViewModel?
) {
    val isEsqueciSenhaVisible = remember { mutableStateOf(false) }

    var errorMessage by remember {
        mutableStateOf("")
    }
   /*
    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }*/

    val email by vm!!.email.collectAsState()
    val senha by vm!!.password.collectAsState()

    val emailColor by vm!!.emailColor.collectAsState()
    val senhaColor by vm!!.passwordColor.collectAsState()


    val state by vm!!.state.observeAsState()

    when (state) {
        is LoginScreenState.Loading -> {
            Loading("Entrando...")
        }

        is LoginScreenState.Success -> {
            navigateToHistorico()
        }

        is LoginScreenState.Error, null -> {
            errorMessage = (state as LoginScreenState.Error).message
        }
    }

    Scaffold(
        topBar = {
            TopAppBarLoginCadastro(
                titulo = "Login",
                navigateToPage = { navigateToHome() }
            )
        }
    ) { padding ->
        Box(
            contentAlignment = Alignment.Center,
            modifier = modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column(
                modifier = modifier
                    .align(Alignment.Center)
                    .padding(26.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.titulo_tela_login),
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = modifier.height(44.dp))

                Row {
                    Text(
                        text = errorMessage,
                        color = Color.Red
                    )
                }

                Spacer(modifier = modifier.height(10.dp))

                InputEmail(
                    email = { email },
                    setEmail = { vm!!.validateEmail(it) },
                    inputColor = { emailColor }
                )

                Spacer(modifier = modifier.height(32.dp))

                InputPassword(
                    senha = { senha },
                    setSenha = { vm!!.validatePassword(it) },
                    inputColor = { senhaColor }
                )

                Column(
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Text(
                        color = Preto,
                        text = "Esqueci minha senha!",
                        textAlign = TextAlign.Start,
                        modifier = modifier
                            .clickable { isEsqueciSenhaVisible.value = true }
                    )
                    Row(
                        modifier = modifier
                            .align(Alignment.End)
                    ) {
                        EntryButton(
                            onClick = {
                                try {
                                    vm?.getUser(form = UserForm(email, senha))
                                } finally {
                                    println(state.toString())
                                }
                            }
                        )
                    }

                    /*Text(
                        text = "Seja bem vindo(a), " + state?.body()?.getNome()
                                + "\nemail: " + state?.body()?.getEmail()
                                + "\nUUID: " + state?.body()?.getId()
                    )*/
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

@Composable
fun EsqueciSenhaDialog(onClose: () -> Unit) {

}

@Composable
fun ScreenState(vm: LoginViewModel, form: UserForm) {

}

@Preview
@Composable
fun LoginScreenPreview() {
    BasquiatTheme {
        LoginScreen(
            navigateToHistorico = {},
            navigateToHome = {},
            vm = null
        )
    }
}