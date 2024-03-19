package com.changer.basquiat.ui.components

import android.net.Uri
import android.widget.Toast
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.changer.basquiat.ui.theme.Azul
import com.changer.basquiat.ui.theme.BasquiatTheme
import com.changer.basquiat.ui.theme.Branco

@Preview(showBackground = true)
@Composable
fun BoxConversionPreview() {
    BasquiatTheme {
        BoxConversion(onFileSelected = {})
    }
}

@Composable
fun BoxConversion(
    onFileSelected: (Uri) -> Unit
) {
    val context = LocalContext.current
    val (selectedFile, setSelectedFile) = remember { mutableStateOf<Uri?>(null) }
    val launcher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        if (uri != null) {
            setSelectedFile(uri)
            onFileSelected(uri)
        } else {
            Toast.makeText(context, "Nenhum arquivo selecionado", Toast.LENGTH_SHORT).show()
        }
    }

    LaunchedEffect(key1 = true) {
        launcher.launch("*/*")
    }
    Button(
        colors = ButtonDefaults.outlinedButtonColors(
            containerColor = Azul,
            contentColor = Branco
        ),
        onClick = { launcher.launch("*/*") },
        shape = RoundedCornerShape(8.dp),
        modifier = Modifier.size(310.dp, 47.dp)
    ) {
        Icon(
            imageVector = Icons.Default.AddCircle,
            contentDescription = "Selecionar arquivo"
        )
        Text(
            text = "Faça upload do seu arquivo",
            style = MaterialTheme.typography.labelSmall,
            textAlign = TextAlign.Center
        )
    }

    if (selectedFile != null) {
        Text(text = "${selectedFile.path}")
    }
}
