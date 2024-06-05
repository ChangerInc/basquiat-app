package com.changer.basquiat.presentation.ui.historic

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.components.ErrorView
import com.changer.basquiat.presentation.ui.components.LinearProgress
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.components.UploadButton
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import kotlinx.coroutines.launch

@Preview(showBackground = true)
@Composable
fun HistoricScreenPreview() {
    HistoricScreen(
        navigationToHistoric = {},
        navigationToConversion = {},
        navigationToCircles = {},
        vm = viewModel()
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricScreen(
    navigationToHistoric: () -> Unit,
    navigationToConversion: () -> Unit,
    navigationToCircles: () -> Unit,
    vm: HistoricoViewModel
) {
    val arquivos by vm.arquivos.observeAsState(emptyList())
    val user by vm.authToken.collectAsState(initial = null)
    val context = LocalContext.current
    val state by vm.state.observeAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var loading by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }

    LaunchedEffect(null) {
        vm.getArquivos()
    }

    when (state) {
        is HistoricScreenState.Loading -> {
            val loadingState = (state as HistoricScreenState.Loading).loading
            loading = loadingState
        }

        is HistoricScreenState.Success -> {
            snackbarMessage = (state as HistoricScreenState.Success).message
            LaunchedEffect(HistoricScreenState.Success::class) {
                scope.launch {
                    snackbarHostState.showSnackbar(
                        snackbarMessage,
                        withDismissAction = true,
                        duration = SnackbarDuration.Short
                    )
                }
            }
        }

        is HistoricScreenState.Error -> {
            val errorMessage = (state as HistoricScreenState.Error).message
            ErrorView(message = errorMessage) {
                vm.tryAgain()
            }
        }
    }

    Scaffold(
        topBar = { TopBarLogin(titulo = "Histórico") },
        floatingActionButton = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                tooltip = {
                    PlainTooltip {
                        Text("Adicionar arquivo")
                    }
                },
                state = rememberTooltipState()
            ) {
                UploadButton(
                    context = context
                ) { file ->
                    vm.uploadArquivo(file)
                }
            }
        },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigateBar(
                navigateToHistorico = { navigationToHistoric() },
                navigateToConversao = { navigationToConversion() },
                navigateToCirculos = { navigationToCircles() },
                selectedScreen = 1
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Branco)
                .padding(padding)
        ) {
            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                text = stringResource(id = R.string.boas_vindas_historico) + " ${user?.getNome()}!",
                style = MaterialTheme.typography.titleMedium,
                textAlign = TextAlign.Center
            )

            HorizontalDivider()

            AnimatedVisibility(visible = loading) {
                LinearProgress()
            }
            Historic(
                items = arquivos,
                downloadFile = { idArquivo, fileName ->
                    vm.downloadArquivo(
                        context,
                        idArquivo,
                        fileName
                    )
                },
                deleteFile = { idArquivo -> vm.deleteArquivo(idArquivo) }
            )
        }
    }
}