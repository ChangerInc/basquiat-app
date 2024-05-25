package com.changer.basquiat.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.ui.theme.BasquiatTheme

@Composable
fun Loading(
    loadingMessage: String,
    modifier: Modifier = Modifier
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = modifier.fillMaxSize()
    ) {
        CircularProgressIndicator(
            color = Color.Black,
            modifier = Modifier
                .size(80.dp)
        )
        Spacer(modifier = Modifier.height(20.dp))
        Text(
            text = loadingMessage,
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LoadingPreview() {
    BasquiatTheme {
            Loading("Carregando suas músicas", Modifier)
    }
}
