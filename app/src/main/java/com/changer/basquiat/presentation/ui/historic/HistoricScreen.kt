package com.changer.basquiat.presentation.ui.historic

import android.net.Uri
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.PlainTooltip
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TooltipBox
import androidx.compose.material3.TooltipDefaults
import androidx.compose.material3.rememberTooltipState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.changer.basquiat.R
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.viewmodel.HistoricoViewModel
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@Preview(showBackground = true)
@Composable
fun HistoricScreenPreview() {
    HistoricScreen(
        navigationToHistoric = {},
        navigationToConversion = {},
        navigationToCircles = {},
        vm = viewModel(),
        userPreferences = null
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoricScreen(
    navigationToHistoric: () -> Unit,
    navigationToConversion: () -> Unit,
    navigationToCircles: () -> Unit,
    vm: HistoricoViewModel,
    userPreferences: UserPreferences?
) {
    val arquivos by vm.arquivos.observeAsState(emptyList())
    val user by userPreferences!!.authToken.collectAsState(initial = null)
    val context = LocalContext.current
    val fileChooserLauncher =
        rememberLauncherForActivityResult(contract = ActivityResultContracts.GetContent()) { uri: Uri? ->
            uri?.let {
                val fileName =
                    context.contentResolver.query(it, null, null, null, null)?.use { cursor ->
                        val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                        cursor.moveToFirst()
                        cursor.getString(nameIndex)
                    }
                val inputStream = context.contentResolver.openInputStream(it)
                inputStream?.let { stream ->
                    val requestFile = stream.readBytes().toRequestBody()
                    val body = MultipartBody.Part.createFormData("file", fileName, requestFile)
                    vm.uploadArquivo(user?.getId(), body)
                }
            }
        }

    LaunchedEffect(key1 = Unit) {
        vm.getArquivos(user?.getId())
    }

    Scaffold(
        topBar = { TopBarLogin(titulo = "Historico") },
        floatingActionButton = {
            TooltipBox(
                positionProvider = TooltipDefaults.rememberPlainTooltipPositionProvider(),
                tooltip = {
                    PlainTooltip {
                        Text("Add to favorites")
                    }
                },
                state = rememberTooltipState()
            ) {
                FloatingActionButton(
                    modifier = Modifier.size(65.dp),
                    containerColor = Azul,
                    onClick = { fileChooserLauncher.launch("application/*") }) {
                    Icon(
                        Icons.Filled.UploadFile,
                        "Upload File",
                        modifier = Modifier.size(40.dp),
                        tint = Branco
                    )
                }
            }
        }, floatingActionButtonPosition = FabPosition.End, bottomBar = {
            NavigateBar(
                navigateToHistorico = { navigationToHistoric() },
                navigateToConversao = { navigationToConversion() },
                navigateToCirculos = { navigationToCircles() },
                selectedScreen = 1
            )
        }) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
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
            Historic(items = arquivos)
        }
    }
}
