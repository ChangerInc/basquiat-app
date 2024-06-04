package com.changer.basquiat.presentation.ui.historic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import java.math.BigDecimal
import java.util.UUID

@Preview(showBackground = true)
@Composable
fun HistoricPreview() {
    Historic(items = List(0) {
        Arquivo(
            idArquivo = UUID.randomUUID(),
            nome = "Nome do arquivo",
            criacao = "2021-10-10T00:00:00.000000",
            tamanho = BigDecimal(100),
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
        if (items!!.isNotEmpty()) {
            items.forEach { arquivo ->
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
        } else {
            item {
                Text(
                    text = "Nenhum arquivo no histórico",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}




