package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto
import java.util.UUID

@Composable
fun Invites(
    items: List<Convites>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Branco, RoundedCornerShape(0.dp, 0.dp, 16.dp, 16.dp))
            .padding(10.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (items.isNotEmpty()) {
            items(items) { item ->
                InvitesItem(
                    fotoAnfitriao = item.fotoAnfitriao,
                    anfitriao = item.anfitriao,
                    nomeCirculo = item.nomeCirculo,
                    horario = item.horario,
                    onClickAceitar = { /*TODO*/ },
                    onClickRecusar = { /*TODO*/ }
                )
            }
        } else {
            item {
                Text(
                    text = "Nenhum convite recebido",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    color = Preto
                )
            }
        }
    }
}

@Preview
@Composable
private fun InvitesPreview() {
    Invites(
        items = List(10) {
            Convites(
                fotoAnfitriao = "https://www.google.com",
                anfitriao = "Anfitrião",
                nomeCirculo = "Nome Circulo",
                idCirculo = UUID.randomUUID(),
                horario = "2022-10-10T10:10:10.000000"
            )
        }
    )
}