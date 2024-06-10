package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.presentation.ui.theme.Preto
import java.util.UUID

@Composable
fun GenericDialog(
    title: String,
    body: @Composable () -> Unit,
    onDismiss: (Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    Dialog(onDismissRequest = { onDismiss(false) }) {
        Column(
            modifier = modifier
                .background(
                    Color.Transparent,
                    RoundedCornerShape(16.dp)
                )
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(50.dp)
                    .background(
                        Color.White,
                        RoundedCornerShape(16.dp, 16.dp, 0.dp, 0.dp)
                    ),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Box(
                    modifier = Modifier
                        .padding(24.dp)
                )
                Text(
                    text = title,
                    fontSize = 24.sp,
                    color = Preto
                )
                IconButton(
                    onClick = { onDismiss(false) }
                ) {
                    Icon(
                        imageVector = Icons.Filled.Close,
                        contentDescription = "Fechar",
                        tint = Preto
                    )
                }
            }
            body()
        }
    }
}

@Preview
@Composable
private fun GenericDialogPreview() {
    GenericDialog(
        title = "Teste",
        body = {
            Invites(
                items = List(10) {
                    Convites(
                        fotoAnfitriao = "https://www.google.com",
                        anfitriao = "Anfitrião",
                        nomeCirculo = "Nome do círculo",
                        idCirculo = UUID.randomUUID(),
                        horario = "2021-10-10T00:00:00.000000",
                    )
                }
            )
        },
        onDismiss = { /*TODO*/ }
    )
}