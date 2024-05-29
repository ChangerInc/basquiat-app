package com.changer.basquiat.ui.historic.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.common.data.Arquivo

@Preview(showBackground = true)
@Composable
fun HistoricPreview() {
    Historic(items = List(50) {
        Arquivo(
            idArquivo = java.util.UUID.randomUUID(),
            nome = "Nome do arquivo",
            criacao = "2021-10-10T00:00:00.000000",
            tamanho = java.math.BigDecimal(100),
            extensao = "pdf",
            urlArquivo = "https://www.google.com"
        )
    })
}

@Composable
fun Historic(
    items: List<Arquivo>?
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        items?.forEach { arquivo ->
            item {
                FileItem(
                    criacao = arquivo.criacao,
                    extensao = arquivo.extensao,
                    idArquivo = arquivo.idArquivo.toString(),
                    nome = arquivo.nome,
                    tamanho = arquivo.tamanho,
                    urlArquivo = arquivo.urlArquivo
                )
            }
        }
    }
}




