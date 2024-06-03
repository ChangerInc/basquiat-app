package com.changer.basquiat.presentation.ui.historic

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import java.math.BigDecimal
import java.util.UUID

@Preview(showBackground = true)
@Composable
fun HistoricPreview() {
    Historic(
        items = List(10) {
            Arquivo(
                idArquivo = UUID.randomUUID(),
                nome = "Nome do arquivo",
                criacao = "2021-10-10T00:00:00.000000",
                tamanho = BigDecimal(100),
                extensao = "pdf",
                urlArquivo = "https://www.google.com"
            )
        },
        downloadFile = { _, _ -> },
        deleteFile = {}
    )
}

@Composable
fun Historic(
    items: List<Arquivo>?,
    downloadFile: (UUID, String) -> Unit,
    deleteFile: (UUID) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        contentPadding = PaddingValues(12.dp),
        verticalArrangement = Arrangement.spacedBy(5.dp)
    ) {
        if (items!!.isNotEmpty()) {
            items.forEach { arquivo ->
                item {
                    FileItem(
                        criacao = arquivo.criacao,
                        extensao = arquivo.extensao,
                        nome = arquivo.nome,
                        tamanho = arquivo.tamanho,
                        downloadFile = { downloadFile(arquivo.idArquivo, arquivo.nome) },
                        deleteFile = { deleteFile(arquivo.idArquivo) }
                    )
                    if (items.indexOf(arquivo) != items.size - 1) {
                        Spacer(modifier = Modifier.height(5.dp))
                        HorizontalDivider(color = CinzaClaro)
                    }
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




