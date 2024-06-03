package com.changer.basquiat.presentation.ui.components

import android.content.Context
import android.provider.OpenableColumns
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.UploadFile
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.changer.basquiat.presentation.ui.theme.Azul
import com.changer.basquiat.presentation.ui.theme.Branco
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody

@Composable
fun UploadButton(context: Context, upload: (MultipartBody.Part) -> Unit) {
    val fileChooserLauncher = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.GetContent()
    ) { uri ->
        uri?.let {
            val fileName = context.contentResolver
                .query(it, null, null, null, null)?.use { cursor ->
                    val nameIndex = cursor.getColumnIndex(OpenableColumns.DISPLAY_NAME)
                    cursor.moveToFirst()
                    cursor.getString(nameIndex)
                }
            val inputStream = context.contentResolver.openInputStream(it)
            inputStream?.let { stream ->
                val requestFile = stream.readBytes().toRequestBody()
                val body = MultipartBody.Part.createFormData("file", fileName, requestFile)
                upload(body)
            }
        }
    }

    FloatingActionButton(
        modifier = Modifier.size(65.dp),
        containerColor = Azul,
        onClick = {
            fileChooserLauncher.launch("*/*")
        }) {
        Icon(
            Icons.Filled.UploadFile,
            "Upload File",
            modifier = Modifier.size(40.dp),
            tint = Branco
        )
    }
}