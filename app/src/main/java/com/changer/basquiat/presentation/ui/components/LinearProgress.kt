package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme

@Composable
fun LinearProgress(
    modifier: Modifier = Modifier
) {
    Row {
        LinearProgressIndicator(
            color = Azul,
            modifier = modifier
                .fillMaxWidth()
                .background(Color.Transparent, shape = RoundedCornerShape(10.dp))
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LinearProgressPreview() {
    BasquiatTheme {
        LinearProgress()
    }
}
