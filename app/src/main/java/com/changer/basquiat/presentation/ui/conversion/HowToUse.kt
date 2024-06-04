package com.changer.basquiat.presentation.ui.conversion

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.R
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto

@Composable
fun HowToUse() {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .height(160.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = stringResource(R.string.como_usar_tela_conversao),
            fontSize = 24.sp,
            color = Branco,
            fontWeight = FontWeight.Bold
        )
        Topic(stringResource(R.string.como_usar_passo_um_tela_conversao), 1)
        Topic(stringResource(R.string.como_usar_passo_dois_tela_conversao), 2)
        Topic(stringResource(R.string.como_usar_passo_tres_tela_conversao), 3)
    }
}

@Composable
fun Topic(
    text: String,
    number: Int
) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        Box(
            modifier = Modifier
                .width(30.dp)
                .height(30.dp)
                .background(Branco, shape = CircleShape),
        ) {
            Text(
                modifier = Modifier.align(Alignment.Center),
                text = number.toString(),
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Azul
            )
        }
        Text(
            modifier = Modifier
                .fillMaxWidth(),
            text = text,
            fontWeight = FontWeight.Bold,
            color = Branco,
            maxLines = 2
        )
    }
}

@Preview
@Composable
fun HowToUsePreview() {
    HowToUse()
}