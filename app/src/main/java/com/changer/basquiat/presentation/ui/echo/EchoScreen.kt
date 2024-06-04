package com.changer.basquiat.presentation.ui.echo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.changer.basquiat.presentation.viewmodel.EchoViewModel

@Composable
fun EchoScreen(viewModel: EchoViewModel = viewModel()) {
    val echoResponse = viewModel.echoResponse.observeAsState()

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Button(
                onClick = { viewModel.testEcho() },
                modifier = Modifier
                    .padding(16.dp) // Adicione margem para um espaçamento melhor
            ) {
                Text(text = "ECHO!")
            }

            echoResponse.value?.let {
                // Aqui você pode exibir a resposta em algum lugar na sua UI
                // Por exemplo:
                Text(text = it)
            }
        }
    }
}
