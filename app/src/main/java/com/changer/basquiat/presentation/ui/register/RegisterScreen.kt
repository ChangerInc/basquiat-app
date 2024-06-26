package com.changer.basquiat.presentation.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.components.ErrorView
import com.changer.basquiat.presentation.ui.components.GenericDialog
import com.changer.basquiat.presentation.ui.components.InputConfirmPassword
import com.changer.basquiat.presentation.ui.components.InputEmail
import com.changer.basquiat.presentation.ui.components.InputName
import com.changer.basquiat.presentation.ui.components.InputPassword
import com.changer.basquiat.presentation.ui.components.Loading
import com.changer.basquiat.presentation.ui.components.TopAppBarLoginCadastro
import com.changer.basquiat.presentation.ui.home.RegisterButton
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Preto
import com.changer.basquiat.presentation.viewmodel.RegisterViewModel

@Preview
@Composable
fun RegisterScreenPreview() {
    BasquiatTheme {
        RegisterScreen(
            navigateToLogin = {},
            navigateToHome = {},
            vm = viewModel<RegisterViewModel>(),
        )
    }
}

@Composable
fun RegisterScreen(
    vm: RegisterViewModel,
    modifier: Modifier = Modifier,
    navigateToLogin: () -> Unit,
    navigateToHome: () -> Unit
) {
    val name by vm.name.collectAsState()
    val email by vm.email.collectAsState()
    val password by vm.password.collectAsState()
    val passwordConfirm by vm.passwordConfirm.collectAsState()

    val nameColor by vm.nameColor.collectAsState()
    val emailColor by vm.emailColor.collectAsState()
    val passwordColor by vm.passwordColor.collectAsState()
    val passwordConfirmColor by vm.passwordConfirmColor.collectAsState()

    var isOpenTermsDialog by remember { mutableStateOf(false) }

    val checkedState = remember { mutableStateOf(false) }

    fun openTermsDialog(isOpen: Boolean) {
        isOpenTermsDialog = isOpen
    }

    val nameFocusRequester = remember {
        FocusRequester()
    }
    val emailFocusRequester = remember {
        FocusRequester()
    }

    val passwordFocusRequester = remember {
        FocusRequester()
    }

    val confirmPasswordFocusRequester = remember {
        FocusRequester()
    }

    var errorName by remember {
        mutableStateOf("")
    }

    var errorEmail by remember {
        mutableStateOf("")
    }

    var errorPassword by remember {
        mutableStateOf("")
    }

    var errorConfirmPassword by remember {
        mutableStateOf("")
    }

    var errorForm by remember {
        mutableStateOf(false)
    }

    val state by vm.state.observeAsState()

    LaunchedEffect(Unit) {
        nameFocusRequester.requestFocus()
    }

    when (state) {
        is RegisterScreenState.Normalize -> {
            if (isOpenTermsDialog) {
                GenericDialog(
                    title = "Termo de Adequação à LGPD",
                    body = {
                        TermsOfUse(
                            modifier = Modifier
                                .fillMaxHeight(0.6f)
                        )
                    },
                    onDismiss = { isOpen ->
                        openTermsDialog(isOpen)
                    })
            }
            Scaffold(
                topBar = {
                    TopAppBarLoginCadastro(
                        titulo = "Cadastro",
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
                            text = stringResource(id = R.string.titulo_tela_cadastro),
                            style = MaterialTheme.typography.titleMedium,
                            textAlign = TextAlign.Left
                        )
                        Spacer(modifier = modifier.height(53.dp))

                        if (name.length !in (3..40) && name != "") {
                            errorName = "O nome deve ter no mínimo 3 caracteres e no máximo 40"
                            errorForm = true
                        } else {
                            errorName = ""
                            errorForm = false
                        }

                        Text(
                            text = errorName,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )

                        InputName(
                            name = { name },
                            setName = { vm.validateName(it) },
                            inputColor = { nameColor },
                            focusRequester = { nameFocusRequester },
                            onImeAction = { emailFocusRequester.requestFocus() }
                        )

                        Spacer(modifier = modifier.height(11.dp))

                        if (!(android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches())
                            && email != ""
                        ) {
                            errorEmail = "Email inválido"
                            errorForm = true
                        } else {
                            errorEmail = ""
                            errorForm = false
                        }

                        Text(
                            text = errorEmail,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )

                        InputEmail(
                            email = { email },
                            setEmail = { vm.validateEmail(it) },
                            inputColor = { emailColor },
                            focusRequester = { emailFocusRequester },
                            onImeAction = { passwordFocusRequester.requestFocus() }
                        )

                        Spacer(modifier = modifier.height(11.dp))

                        if (password.length !in (6..20) && password != "") {
                            errorPassword = "A senha deve ter no mínimo 6 caracteres e no máximo 20"
                            errorForm = true
                        } else {
                            errorPassword = ""
                            errorForm = false
                        }

                        Text(
                            text = errorPassword,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )

                        InputPassword(
                            senha = { password },
                            setSenha = { vm.validatePassword(it) },
                            inputColor = { passwordColor },
                            focusRequester = { passwordFocusRequester },
                            onImeAction = { confirmPasswordFocusRequester.requestFocus() }
                        )

                        Spacer(modifier = modifier.height(11.dp))

                        if (password != passwordConfirm && passwordConfirm != "") {
                            errorConfirmPassword = "As senhas devem corresponder"
                            errorForm = true
                        } else {
                            errorConfirmPassword = ""
                            errorForm = false
                        }

                        Text(
                            text = errorConfirmPassword,
                            color = Color.Red,
                            style = MaterialTheme.typography.bodySmall
                        )

                        InputConfirmPassword(
                            senha = { passwordConfirm },
                            setSenha = { vm.validatePasswordConfirm(it) },
                            inputColor = { passwordConfirmColor },
                            focusRequester = { confirmPasswordFocusRequester },
                            onImeAction = { vm.registerSend() }
                        )

                        Column(
                            modifier = modifier
                                .fillMaxWidth()
                                .padding(top = 8.dp)
                        ) {
                            Row(
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Checkbox(
                                    checked = checkedState.value,
                                    onCheckedChange = { checkedState.value = it }
                                )
                                Text(
                                    text = "Li e concordo com os",
                                    fontSize = 16.sp,
                                    color = Preto
                                )
                                TextButton(onClick = { openTermsDialog(true) }) {
                                    Text(
                                        text = "termos de uso",
                                        fontSize = 16.sp,
                                        color = Azul
                                    )
                                }
                            }
                            Text(
                                color = Azul,
                                text = "Já sou cadastrado!",
                                textAlign = TextAlign.Start,
                                modifier = modifier
                                    .clickable { navigateToLogin() }
                            )
                            Row(
                                modifier = modifier
                                    .align(Alignment.End)
                                    .padding(top = 8.dp)
                            ) {
                                RegisterButton(
                                    onClick = { if (!errorForm && checkedState.value) vm.registerSend() },
                                    enabled = !errorForm && checkedState.value && name != ""
                                            && email != "" && password != "" && passwordConfirm != ""
                                )
                            }
                        }
                    }
                }
            }
        }

        is RegisterScreenState.Loading -> {
            Loading(loadingMessage = "Enviando cadastro...")
        }

        is RegisterScreenState.Error, null -> {
            ErrorView(message = (state as RegisterScreenState.Error).message) {
                vm.tryAgain()
            }
        }

        is RegisterScreenState.Success -> {
            navigateToLogin()
        }
    }
}