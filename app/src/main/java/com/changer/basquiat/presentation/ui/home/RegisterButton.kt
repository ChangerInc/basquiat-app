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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.BasquiatTheme
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Cinza
import com.changer.basquiat.presentation.ui.theme.CinzaClaro

@Preview(showBackground = true)
@Composable
fun RegisterButtonPreview(){
    BasquiatTheme {
        RegisterButton(
            onClick = {},
            enabled = true
        )
    }
}

@Composable
fun RegisterButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    enabled: Boolean
) {
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Azul,
            contentColor = Branco,
            disabledContentColor = Cinza,
            disabledContainerColor = CinzaClaro
        ),
        enabled = enabled,
        onClick = onClick,
        modifier = modifier
            .size(width = 185.dp, height = 53.dp)
            .background(
                color = if (enabled) Azul else CinzaClaro,
                shape = RoundedCornerShape(6.dp)
            )
    ) {
        Text(
            color = Branco,
            text = "Cadastrar-se",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )
    }
}