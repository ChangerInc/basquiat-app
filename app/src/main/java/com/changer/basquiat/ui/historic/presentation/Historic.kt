package com.changer.basquiat.ui.historic.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.ui.historic.data.HistoricItem

@Preview(showBackground = true)
@Composable
fun HistoricPreview() {
    Historic(items = List(50) {
        HistoricItem(
            criacao = java.util.Date(),
            extensao = "pdf",
            idArquivo = "id$it",
            nome = "Arquivo $it",
            tamanho = it.toLong(),
            urlArquivo = "url$it"
        )
    })
}

@Composable
fun Historic(
    items: List<HistoricItem>
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        this.items(items) { item ->
            FileItem(
                criacao = item.criacao,
                extensao = item.extensao,
                idArquivo = item.idArquivo,
                nome = item.nome,
                tamanho = item.tamanho,
                urlArquivo = item.urlArquivo
            )
        }
    }
}




