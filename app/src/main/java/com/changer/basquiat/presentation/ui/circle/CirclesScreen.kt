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
import com.changer.basquiat.presentation.ui.components.TopBarLogin
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme

@Composable
@Preview(showBackground = true)
fun CircleScreenPreview() {
    BasquiatTheme {
        CircleScreen()
    }
}

@Composable
fun CircleScreen() {
    Scaffold (
        topBar = { TopBarLogin(titulo = "Circulos") }
    ) { padding ->
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
            .padding(padding)
        ) {
            Column (
                Modifier.align(Alignment.TopCenter)
            ) {

            }
        }
    }
}