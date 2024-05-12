package com.changer.basquiat.ui.components

import android.content.Context
import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import androidx.documentfile.provider.DocumentFile
import com.changer.basquiat.ui.theme.Azul
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco

@Preview(showBackground = true)
@Composable
fun BoxConversionPreview() {
    BasquiatTheme {
        BoxConversion(onFileSelected = { _, _ -> })
    }
}

@Composable
fun BoxConversion(
    onFileSelected: (Uri, String) -> Unit,
) {
    val fileName = remember { mutableStateOf("Selecionar arquivo") }
    val context = LocalContext.current
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        handleFileSelection(uri, context, onFileSelected, fileName)
    }

    LaunchedEffect(key1 = true) {
        launcher.launch("*/*")
    }

    UploadButton(fileName.value) { launcher.launch("*/*") }
}

@Composable
fun UploadButton(fileName: String, onClick: () -> Unit) {
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Azul,
            contentColor = Branco
        ),
        onClick = onClick,
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(310.dp, 47.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.AddCircle,
                contentDescription = "Selecionar arquivo"
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = fileName,
                style = MaterialTheme.typography.labelSmall,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .widthIn(max = 200.dp),
                maxLines = 1,
                overflow = TextOverflow.Ellipsis
            )
        }
    }
}

fun handleFileSelection(
    uri: Uri?,
    context: Context,
    onFileSelected: (Uri, String) -> Unit,
    fileName: MutableState<String>
) {
    if (uri != null) {/*
        val documentFile = DocumentFile.fromSingleUri(context, uri)
        val name = documentFile?.name ?: "Unknown"
        onFileSelected(uri, name)
        fileName.value = name*/
    } else {
        Toast.makeText(context, "Nenhum arquivo selecionado", Toast.LENGTH_SHORT).show()
    }
}