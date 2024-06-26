package com.changer.basquiat.presentation.ui.conversion

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.components.BoxConversion
import com.changer.basquiat.presentation.ui.components.ChangePhoto
import com.changer.basquiat.presentation.ui.components.ErrorView
import com.changer.basquiat.presentation.ui.components.GenericDialog
import com.changer.basquiat.presentation.ui.components.Invites
import com.changer.basquiat.presentation.ui.components.Loading
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel
import kotlinx.coroutines.launch

@Composable
fun ConversionScreen(
    navController: NavHostController,
    vm: ConversionViewModel,
    signOut: () -> Unit = {}
) {
    val screens = listOf(
        Screen.Conversion,
        Screen.Historic,
        Screen.Circles
    )

    val user by vm.authToken.collectAsState(initial = null)
    val context = LocalContext.current
    val showDialog = vm.showDialog.observeAsState()
    var openDialogInvites by remember { mutableStateOf(false) }
    var openDialogChangePhoto by remember { mutableStateOf(false) }
    val countNotifications by vm.countNotifications.observeAsState()
    val invites by vm.convites.observeAsState()
    val state by vm.state.observeAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var loading by remember { mutableStateOf(false) }
    var loadingMessage by remember { mutableStateOf("") }
    var snackbarMessage by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(emptyList<String>()) }

    fun updateListOptions(list: List<String>) {
        options = list
    }

    fun handleDialogInvites(isOpen: Boolean) {
        openDialogInvites = isOpen
    }

    fun handleDialogChangePhoto(isOpen: Boolean) {
        openDialogChangePhoto = isOpen
    }

    LaunchedEffect(Unit) {
        vm.getQtdNotificacoes()
        vm.getConvites()
    }

    when (state) {
        is ConversionScreenState.Loading -> {
            loading = (state as ConversionScreenState.Loading).loading
            loadingMessage = (state as ConversionScreenState.Loading).message
        }

        is ConversionScreenState.Success -> {
            snackbarMessage = (state as ConversionScreenState.Success).message
            LaunchedEffect(ConversionScreenState.Success::class) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        snackbarMessage,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }

        is ConversionScreenState.Error -> {
            val errorMessage = (state as ConversionScreenState.Error).message
            ErrorView(message = errorMessage) {
                vm.tryAgain()
            }
        }
    }

    Scaffold(
        topBar = {
            user?.let {
                TopBarLogin(
                    titulo = "Conversão",
                    notification = countNotifications,
                    url = it.getFotoPerfil(),
                    openDialogInvites = { isOpen ->
                        handleDialogInvites(isOpen)
                    },
                    openDialogChangePhoto = { isOpen ->
                        handleDialogChangePhoto(isOpen)
                    },
                    logout = {
                        signOut()
                    }
                )
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigateBar(
                navController = navController,
                screens = screens,
                selectedScreen = Screen.Conversion
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        if (openDialogInvites) {
            GenericDialog(
                title = "Convites",
                body = {
                    Invites(
                        items = invites ?: emptyList(),
                        modifier = Modifier
                            .fillMaxHeight(0.6f)
                            .background(Branco, RoundedCornerShape(16.dp))
                    )
                },
                onDismiss = { isOpen ->
                    handleDialogInvites(isOpen)
                }
            )
        }
        if (openDialogChangePhoto) {
            GenericDialog(
                title = "Mudar foto de perfil",
                body = {
                    ChangePhoto(
                        onFileSelected = { file ->
                            vm.patchFoto(file)
                        },
                        modifier = Modifier
                            .fillMaxHeight(0.4f)
                            .background(Branco, RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
                    )
                },
                onDismiss = { isOpen ->
                    handleDialogChangePhoto(isOpen)
                }
            )
        }
        AnimatedVisibility(visible = loading) {
            Loading(loadingMessage)
        }
        AnimatedVisibility(visible = !loading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(padding),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.background_conversion),
                    contentDescription = "Background",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier.matchParentSize()
                )

                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(12.dp)
                        .background(Color.Transparent),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.SpaceAround
                ) {
                    Text(
                        text = stringResource(R.string.titulo_tela_conversao),
                        fontSize = 48.sp,
                        fontWeight = FontWeight.Bold,
                        color = Branco,
                        lineHeight = 64.sp
                    )
                    BoxConversion(
                        onFileSelected = { file ->
                            vm.enviarArquivo(file)
                        },
                        options = { list ->
                            updateListOptions(list)
                        }
                    )
                    if (showDialog.value == true) {
                        DialogConversion(
                            options = options,
                            onDismissRequest = { showDialog.value },
                            onOptionSelected = { extensao ->
                                vm.converterArquivo(context, extensao)
                            }
                        )
                    }
                    HowToUse()
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversionScreenPreview() {
    ConversionScreen(
        navController = rememberNavController(),
        vm = viewModel()
    )
}