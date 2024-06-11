package com.changer.basquiat.presentation.ui.circle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.domain.model.UserMember
import com.changer.basquiat.presentation.ui.components.ErrorView
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.viewmodel.CircleViewModel
import kotlinx.coroutines.launch
import java.math.BigDecimal
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleScreenPreview() {
    BasquiatTheme {
        CircleScreen(
            navController = rememberNavController(),
            vm = viewModel()
        )
    }
}

@Composable
fun CircleScreen(
    navController: NavHostController,
    vm: CircleViewModel
) {
    val screens = listOf(
        Screen.Conversion,
        Screen.Historic,
        Screen.Circles
    )

    val state by vm.state.observeAsState()
    val user by vm.authToken.collectAsState(initial = null)
    var loading by remember { mutableStateOf(false) }
    val countNotifications by vm.countNotifications.observeAsState()
    val invites by vm.convites.observeAsState()
    var openDialog by remember { mutableStateOf(false) }
    val snackbarHostState = remember { SnackbarHostState() }
    val scope = rememberCoroutineScope()
    var snackbarMessage by remember { mutableStateOf("") }

    var isDialogOpen by remember { mutableStateOf(false) }

    fun handleDialog(isOpen: Boolean) {
        openDialog = isOpen
    }

    LaunchedEffect(null) {
        vm.getCirculos()
        vm.getQtdNotificacoes()
        vm.getConvites()
    }

    when (state) {
        is CircleScreenState.Loading -> {
            val loadingState = (state as CircleScreenState.Loading).loading
            loading = loadingState
        }

        is CircleScreenState.Success -> {
            snackbarMessage = (state as CircleScreenState.Success).message
            LaunchedEffect(CircleScreenState.Success::class) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        snackbarMessage,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }

        is CircleScreenState.Error -> {
            ErrorView(message = (state as CircleScreenState.Error).message) {
                vm.tryAgain()
            }
        }
    }

    Scaffold(
        topBar = {
            user?.let {
                TopBarLogin(
                    titulo = "Circulos",
                    notification = countNotifications,
                    url = it.getFotoPerfil(),
                    openDialog = { isOpen ->
                        handleDialog(isOpen)
                    }
                )
            }
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { isDialogOpen = true },
                contentColor = Azul
            ) {
                Icon(imageVector = Icons.Default.Add, contentDescription = "Adicionar")
            }
        },
        bottomBar = {
            NavigateBar(
                navController = navController,
                screens = screens,
                selectedScreen = Screen.Circles
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column(
                Modifier.align(Alignment.TopCenter)
            ) {
                CirclesList(groups = listOf())
            }
        }
    }
}