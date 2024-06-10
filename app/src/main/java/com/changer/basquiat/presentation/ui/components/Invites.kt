package com.changer.basquiat.presentation.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.presentation.ui.theme.Branco
import java.util.UUID

@Composable
fun Invites(
    items: List<Convites>,
    modifier: Modifier = Modifier
) {
    LazyColumn(
        modifier = modifier
            .fillMaxWidth()
            .background(Branco)
            .padding(10.dp, 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
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