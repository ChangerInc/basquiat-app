package com.changer.basquiat.presentation.ui.circle

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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.presentation.ui.historic.FileItem
import com.changer.basquiat.presentation.ui.theme.CinzaClaro
import java.math.BigDecimal
import java.util.UUID

@Composable
@Preview(showBackground = true)
fun CircleHistoricPreview() {
    val lista = List(10) {
        Arquivo(
            idArquivo = UUID.randomUUID(),
            nome = "Nome do arquivo",
            criacao = "2021-10-10T00:00:00.000000",
            tamanho = BigDecimal(100),
            extensao = "pdf",
            urlArquivo = "https://www.google.com"
        )
    }

    CircleHistoric(
        items = listOf(),
        downloadFile = { _, _ -> },
    ) {

    }
}

@Composable
fun CircleHistoric(
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
                    text = "Nenhum arquivo no círculo",
                    modifier = Modifier.fillMaxSize(),
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp
                )
            }
        }
    }
}