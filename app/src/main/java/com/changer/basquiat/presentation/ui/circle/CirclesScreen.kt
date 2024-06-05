package com.changer.basquiat.presentation.ui.circle

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleScreenPreview() {
    BasquiatTheme {
        CircleScreen(
            navigationToHistoric = {},
            navigationToConversion = {},
            navigationToCircles = {}
        )
    }
}

@Composable
fun CircleScreen(
    navigationToHistoric: () -> Unit,
    navigationToConversion: () -> Unit,
    navigationToCircles: () -> Unit,
) {
    Scaffold(
        topBar = { TopBarLogin(titulo = "Circulos") },
        bottomBar = {
            NavigateBar(
                navigateToHistorico = { navigationToHistoric() },
                navigateToConversao = { navigationToConversion() },
                navigateToCirculos = { navigationToCircles() },
                selectedScreen = 2
            )
        },
    ) { padding ->
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.White)
                .padding(padding)
        ) {
            val staticList = listOf(
                Circulo(
                    id = UUID.randomUUID(),
                    nomeCirculo = "Changer",
                    dono = UUID.randomUUID(),
                    arquivos = listOf()
                ),
                Circulo(
                    id = UUID.randomUUID(),
                    nomeCirculo = "Backstreet ",
                    dono = UUID.randomUUID(),
                    arquivos = listOf()
                ),
                Circulo(
                    id = UUID.randomUUID(),
                    nomeCirculo = "Bad Boys",
                    dono = UUID.randomUUID(),
                    arquivos = listOf()
                ),
                Circulo(
                    id = UUID.randomUUID(),
                    nomeCirculo = "The Boys",
                    dono = UUID.randomUUID(),
                    arquivos = listOf()
                )
            )
            Column(
                Modifier.align(Alignment.TopCenter)
            ) {
                CirclesList(groups = listOf())
            }
        }
    }
}