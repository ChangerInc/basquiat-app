package com.changer.basquiat.ui.historic.presentation

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.ArrowDropUp
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Download
import androidx.compose.material.icons.filled.InsertDriveFile
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.ui.theme.Branco
import com.changer.basquiat.ui.theme.CinzaClaro
import com.changer.basquiat.ui.theme.Preto
import java.math.BigDecimal
import java.text.SimpleDateFormat
import java.util.Locale

@Preview(showBackground = false)
@Composable
fun PreviewFileItem() {
    FileItem(
        criacao = "2021-10-10T00:00:00.000000",
        extensao = "pdf",
        idArquivo = "1",
        nome = "Nome do arquivo grande para testar o overflow de texto em uma linha",
        tamanho = BigDecimal(100),
        urlArquivo = "https://www.google.com"
    )
}

@Composable
fun FileItem(
    criacao: String,
    extensao: String,
    idArquivo: String,
    nome: String,
    tamanho: BigDecimal,
    urlArquivo: String
) {
    val inputFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSS", Locale.getDefault())
    val outputFormat = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
    val date = inputFormat.parse(criacao)
    val formattedDate = if (date != null) outputFormat.format(date) else "23/05/2000"
    var isExpanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Branco, RoundedCornerShape(8.dp))
            .border(3.dp, CinzaClaro, RoundedCornerShape(8.dp))
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .height(60.dp)
                .background(
                    Branco, if (isExpanded) RoundedCornerShape(
                        topStart = 8.dp,
                        topEnd = 8.dp,
                        bottomStart = 0.dp,
                        bottomEnd = 0.dp
                    ) else RoundedCornerShape(8.dp)
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                imageVector = Icons.Filled.InsertDriveFile,
                contentDescription = null,
                modifier = Modifier.size(50.dp)
            )
            VerticalDivider(color = CinzaClaro)
            Column(
                modifier = Modifier.weight(3f)
            ) {
                Text(
                    color = Preto,
                    modifier = Modifier.fillMaxWidth(),
                    textAlign = TextAlign.Center,
                    text = "Nome",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    color = Preto,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 5.dp),
                    textAlign = TextAlign.Center,
                    text = nome,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis
                )
            }
            VerticalDivider(color = CinzaClaro)
            Column {
                Text(
                    color = Preto,
                    modifier = Modifier.width(75.dp),
                    textAlign = TextAlign.Center,
                    text = "Tamanho",
                    fontWeight = FontWeight.Bold
                )
                Spacer(modifier = Modifier.height(5.dp))
                Text(
                    color = Preto,
                    modifier = Modifier
                        .width(75.dp),
                    textAlign = TextAlign.Center,
                    text = convertFileSize(tamanho.toLong())
                )
            }
            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Icons.Filled.ArrowDropUp else Icons.Filled.ArrowDropDown,
                    contentDescription = null,
                    tint = Preto
                )
            }
        }
        if (isExpanded) {
            HorizontalDivider(
                modifier = Modifier.padding(horizontal = 5.dp),
                color = CinzaClaro
            )
        }
        AnimatedVisibility(
            modifier = Modifier
                .fillMaxWidth()
                .padding(if (isExpanded) 5.dp else 0.dp)
                .background(
                    Branco, if (isExpanded) RoundedCornerShape(
                        topStart = 0.dp,
                        topEnd = 0.dp,
                        bottomStart = 8.dp,
                        bottomEnd = 8.dp
                    ) else RoundedCornerShape(8.dp)
                ),
            visible = isExpanded
        ) {
            Row(
                modifier = Modifier
                    .height(50.dp)
                    .background(Branco, RoundedCornerShape(8.dp)),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceAround
            ) {
                Column {
                    Text(
                        color = Preto,
                        textAlign = TextAlign.Center,
                        text = "Data upload",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        color = Preto,
                        textAlign = TextAlign.Center,
                        text = formattedDate
                    )
                }
                VerticalDivider(color = CinzaClaro)
                Column {
                    Text(
                        color = Preto,
                        modifier = Modifier.width(75.dp),
                        textAlign = TextAlign.Center,
                        text = "Extensão",
                        fontWeight = FontWeight.Bold
                    )
                    Spacer(modifier = Modifier.height(5.dp))
                    Text(
                        color = Preto,
                        modifier = Modifier.width(75.dp),
                        textAlign = TextAlign.Center,
                        text = extensao
                    )
                }
                VerticalDivider(color = CinzaClaro)
                Row {
                    IconButton(onClick = { /*TODO: Handle click*/ }) {
                        Icon(
                            Icons.Filled.Download,
                            contentDescription = null,
                            tint = Preto
                        )
                    }

                    IconButton(onClick = { /*TODO: Handle click*/ }) {
                        Icon(
                            Icons.Filled.Delete,
                            contentDescription = null,
                            tint = Preto
                        )
                    }
                }
            }
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