package com.changer.basquiat.presentation.ui.home

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
import com.changer.basquiat.ui.theme.Azul
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco

@Preview(showBackground = true)
@Composable
fun RegisterButtonPreview(){
    BasquiatTheme {
        RegisterButton(
            onClick = {}
        )
    }
}

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Azul,
            contentColor = Branco
        ),
        onClick = onClick,
        modifier = modifier
            .size(width = 185.dp, height = 53.dp)
            .background(
                color = Azul,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            color = Branco,
            text = "Cadastrar-se",
            style = MaterialTheme.typography.titleMedium
        )
    }
}