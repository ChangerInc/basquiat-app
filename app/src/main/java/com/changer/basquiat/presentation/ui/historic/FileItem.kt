package com.changer.basquiat.presentation.ui.historic

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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.outlined.FilePresent
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.Branco
import com.changer.basquiat.presentation.ui.theme.Preto
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale

@Preview(showBackground = false)
@Composable
fun PreviewFileItem() {
    FileItem(
        criacao = "2021-10-10T00:00:00.000000",
        extensao = "pdf",
        nome = "Nome do arquivo grande para testar o overflow de texto em uma linha",
        tamanho = BigDecimal(100),
        downloadFile = {},
        deleteFile = {}
    )
}

@Composable
fun FileItem(
    criacao: String,
    extensao: String,
    nome: String,
    tamanho: BigDecimal,
    downloadFile: () -> Unit,
    deleteFile: () -> Unit
) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy HH:mm", Locale.getDefault())
    val date = inputFormat.parse(criacao)
    val formattedDate = if (date != null) outputFormat.format(date) else "23/05/2000"

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(70.dp)
            .background(
                Branco, RoundedCornerShape(12.dp)
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column(
            modifier = Modifier
                .width(40.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Image(
                imageVector = Icons.Outlined.FilePresent,
                contentDescription = null,
                modifier = Modifier.size(40.dp)
            )
            Text(
                color = Preto,
                modifier = Modifier.width(75.dp),
                textAlign = TextAlign.Center,
                text = extensao
            )
        }
        Column(
            modifier = Modifier
                .width(220.dp),
        ) {
            Text(
                color = Preto,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Start,
                text = nome,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
            Spacer(modifier = Modifier.height(5.dp))
            Text(
                modifier = Modifier.fillMaxWidth(),
                color = Preto,
                textAlign = TextAlign.Start,
                text = formattedDate
            )
        }
        Column(
            modifier = Modifier
                .width(100.dp)
                .fillMaxHeight(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row {
                IconButton(onClick = { downloadFile() }) {
                    Icon(
                        Icons.Filled.Download,
                        contentDescription = "Download File",
                        tint = Preto
                    )
                }
                IconButton(onClick = { deleteFile() }) {
                    Icon(
                        Icons.Filled.Delete,
                        contentDescription = "Delete File",
                        tint = Preto
                    )
                }
            }
            Text(
                color = Preto,
                modifier = Modifier
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                text = convertFileSize(tamanho.toLong())
            )
        }
    }
}

fun convertFileSize(size: Long): String {
    val kb: Long = 1024
    val mb = kb * 1024
    val gb = mb * 1024
    val tb = gb * 1024

    return when {
        size < kb -> "$size Bytes"
        size in kb until mb -> "${size / kb} KB"
        size in mb until gb -> "${size / mb} MB"
        size in gb until tb -> "${size / gb} GB"
        else -> "${size / tb} TB"
    }
}