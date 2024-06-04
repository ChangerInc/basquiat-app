package com.changer.basquiat.presentation.ui.conversion

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.components.BoxConversion
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.viewmodel.ConversionViewModel

@Composable
fun ConversionScreen(
    navigationToHistoric: () -> Unit,
    navigationToConversion: () -> Unit,
    navigationToCircles: () -> Unit,
    vm: ConversionViewModel
) {
    val context = LocalContext.current
    val state by vm.state.observeAsState()
    var showDialog = vm.showDialog.observeAsState()
    val scope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    var loading by remember { mutableStateOf(false) }
    var snackbarMessage by remember { mutableStateOf("") }
    var options by remember { mutableStateOf(emptyList<String>()) }

    fun updateListOptions(list: List<String>) {
        options = list
    }

    Scaffold(
        topBar = { TopBarLogin(titulo = "Histórico") },
        floatingActionButtonPosition = FabPosition.End,
        bottomBar = {
            NavigateBar(
                navigateToHistorico = { navigationToHistoric() },
                navigateToConversao = { navigationToConversion() },
                navigateToCirculos = { navigationToCircles() },
                selectedScreen = 0
            )
        },
        snackbarHost = {
            SnackbarHost(hostState = snackbarHostState)
        }
    ) { padding ->
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
                        onDismissRequest = { showDialog = mutableStateOf(false) },
                        onOptionSelected = { extensao ->
                            vm.converterArquivo(extensao)
                        }
                    )
                }
                HowToUse()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ConversionScreenPreview() {
    ConversionScreen(
        navigationToHistoric = {},
        navigationToConversion = {},
        navigationToCircles = {},
        vm = viewModel()
    )
}