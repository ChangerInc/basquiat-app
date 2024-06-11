package com.changer.basquiat.presentation.ui.circle

import androidx.compose.animation.AnimatedVisibility
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
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.domain.model.UserMember
import com.changer.basquiat.presentation.ui.components.LinearProgress
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import java.math.BigDecimal
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
            AnimatedVisibility(visible = false) {
                LinearProgress()
            }

            Column(
                Modifier.align(Alignment.TopCenter)
            ) {
                CirclesList(groups = listOf())
            }
        }
    }
}