package com.changer.basquiat.presentation.ui.circle

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FilePresent
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Composable
fun CircleItem(
    nome: String,
    arquivos: List<Arquivo>
) {
    Row (
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                Branco,
                RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Start
    ) {
        Row (
            modifier = Modifier.width(10.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
        }

        Column (
            modifier = Modifier
                .width(220.dp)
        ) {
            Text(
                color = Preto,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = nome,
                fontSize = 25.sp
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CircleItemPreview() {
    CircleItem(
        nome = "O Neyma",
        arquivos = listOf()
    )
}