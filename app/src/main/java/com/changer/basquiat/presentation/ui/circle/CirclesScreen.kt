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
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.presentation.ui.components.NavigateBar
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.navigate.Screen
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleScreenPreview() {
    BasquiatTheme {
        CircleScreen(
            navController = rememberNavController()
        )
    }
}

@Composable
fun CircleScreen(
    navController: NavHostController
) {
    val screens = listOf(
        Screen.Conversion,
        Screen.Historic,
        Screen.Circles
    )

    Scaffold(
        topBar = {
            TopBarLogin(
                titulo = "Circulos",
                notification = 0,
                url = "",
                openDialogInvites = {},
                openDialogChangePhoto = {  },
                logout = { }
            )
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