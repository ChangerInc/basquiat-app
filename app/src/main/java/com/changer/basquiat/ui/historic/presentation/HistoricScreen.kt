package com.changer.basquiat.ui.historic.presentation

import androidx.compose.foundation.background
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.R
import com.changer.basquiat.ui.components.TopBarLogin
import com.changer.basquiat.ui.login.presentation.EntryButton

@Preview
@Composable
fun HistoricScreenPreview() {
    HistoricScreen(/*"nacchanzinho"*/)
}

@Composable
fun HistoricScreen(/*nome: String*/) {
    Scaffold(
        topBar = { TopBarLogin(titulo = "Historico") }
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            Column(
                modifier = Modifier
                    .align(Alignment.TopCenter)
                    .padding(26.dp)
            ) {
                Text(
                    text = stringResource(id = R.string.boas_vindas_historico)
                            + " "/* + nome*/,
                    style = MaterialTheme.typography.titleMedium,
                    textAlign = TextAlign.Left
                )

                Spacer(modifier = Modifier.height(53.dp))

                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 5.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .align(Alignment.End)
                    ) {
                        EntryButton(
                            onClick = { }
                        )
                    }
                }
            }
        }
    }
}