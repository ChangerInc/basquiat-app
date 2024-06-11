package com.changer.basquiat.presentation.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.CinzaClaro

@Preview(showBackground = true)
@Composable
fun EntryButtonPreview(){
    BasquiatTheme {
        EntryButton(
            onClick = {},
            enabled = true
        )
    }
}

@Composable
fun EntryButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Azul,
            contentColor = Branco,
            disabledContainerColor = CinzaClaro,
            disabledContentColor = Branco
        ),
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .size(width = 146.dp, height = 53.dp)
            .background(
                color = if (enabled) Azul else CinzaClaro,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            color = Branco,
            text = "Entrar",
            style = MaterialTheme.typography.titleMedium
        )
    }
}