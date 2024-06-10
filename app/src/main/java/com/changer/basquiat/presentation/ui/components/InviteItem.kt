package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto
import java.text.SimpleDateFormat
import java.util.Locale

@Composable
fun InvitesItem(
    fotoAnfitriao: String,
    anfitriao: String,
    nomeCirculo: String,
    horario: String,
    onClickAceitar: () -> Unit,
    onClickRecusar: () -> Unit,
    modifier: Modifier = Modifier
) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inputFormat.parse(horario)
    val formattedDate = if (date != null) outputFormat.format(date) else "23/05/2000"

    Row(
        modifier = modifier
            .height(70.dp)
            .background(
                Branco, RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(50.dp)
                .fillMaxHeight()
                .padding(end = 10.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                painter = rememberAsyncImagePainter(model = fotoAnfitriao),
                contentDescription = null,
                modifier = Modifier
                    .size(40.dp)
                    .clip(CircleShape)
            )
        }
        Column(
            modifier = Modifier
                .width(170.dp),
        ) {
            Text(
                color = Preto,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 14.sp,
                textAlign = TextAlign.Start,
                text = "Para: $nomeCirculo",
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(2.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = Preto,
                fontSize = 12.sp,
                textAlign = TextAlign.Start,
                text = "De: $anfitriao"
            )
        }
        Column(
            modifier = Modifier
                .width(110.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                IconButton(onClick = { onClickAceitar() }) {
                    Icon(
                        Icons.Filled.Check,
                        contentDescription = "Aceitar convite",
                        tint = Preto,
                        modifier = Modifier
                            .background(
                                Color.Green.copy(alpha = 0.2f),
                                CircleShape
                            )
                            .padding(5.dp)
                    )
                }
                IconButton(onClick = { onClickRecusar() }) {
                    Icon(
                        Icons.Filled.Close,
                        contentDescription = "Recusar convite",
                        tint = Preto,
                        modifier = Modifier
                            .background(
                                Color.Red.copy(alpha = 0.2f),
                                CircleShape
                            )
                            .padding(5.dp)
                    )
                }
            }
            Text(
                color = Preto,
                modifier = Modifier
                    .fillMaxWidth(),
                fontSize = 10.sp,
                textAlign = TextAlign.Center,
                text = formattedDate
            )
        }
    }
}

@Preview
@Composable
private fun InvitesItemPreview() {
    InvitesItem(
        fotoAnfitriao = "https://www.google.com",
        anfitriao = "Anfitrião",
        nomeCirculo = "Nome do círculo",
        horario = "2022-10-10T10:10:10.000000",
        onClickAceitar = { },
        onClickRecusar = { }
    )
}