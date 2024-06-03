package com.changer.basquiat.presentation.ui.login

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.changer.basquiat.R
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.presentation.ui.components.ErrorView
import com.changer.basquiat.presentation.ui.components.InputEmail
import com.changer.basquiat.presentation.ui.components.InputPassword
import com.changer.basquiat.presentation.ui.components.Loading
import com.changer.basquiat.presentation.ui.components.TopAppBarLoginCadastro
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Preto
import com.changer.basquiat.presentation.viewmodel.LoginViewModel


@Composable
fun LoginScreen(
    modifier: Modifier = Modifier,
    navigateToHistorico: () -> Unit,
    navigateToHome: () -> Unit,
    vm: LoginViewModel
) {
    val isEsqueciSenhaVisible = remember { mutableStateOf(false) }

    var email by remember {
        mutableStateOf("")
    }

    var senha by remember {
        mutableStateOf("")
    }

    val state by vm.state.observeAsState()

    when (state) {
        is LoginScreenState.Normalize -> {
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
                        .verticalScroll(rememberScrollState())
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

                        Spacer(modifier = modifier.height(10.dp))

                        InputEmail({ email }) { newEmail ->
                            email = newEmail
                        }

                        Spacer(modifier = modifier.height(32.dp))

                        InputPassword({ senha }) { newSenha ->
                            senha = newSenha
                        }

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
                                        vm.getUser(form = UserForm(email, senha))
                                    }
                                )
                            }
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

        is LoginScreenState.Loading -> {
            Loading("Entrando...")
        }

        is LoginScreenState.Error, null -> {
            val errorMessage = (state as LoginScreenState.Error).message
            ErrorView(message = errorMessage) {
                vm.tryAgain()
            }
        }

        is LoginScreenState.Success -> {
            val redirect = true
            AnimatedVisibility(visible = redirect) {
                navigateToHistorico()
            }
        }
    }
}

@Composable
fun EsqueciSenhaDialog(onClose: () -> Unit) {

}

@Preview(showBackground = true)
@Composable
fun LoginScreenPreview() {
    BasquiatTheme {
        val vm = viewModel<LoginViewModel>()
        LoginScreen(
            navigateToHistorico = {},
            navigateToHome = {},
            vm = vm
        )
    }
}