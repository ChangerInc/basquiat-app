package com.changer.basquiat.domain

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.provider.OpenableColumns
import android.util.Log
import android.webkit.MimeTypeMap
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.InputStream

class AboutFile {

    fun createFile(
        context: Context,
        fileName: String
    ): Uri? {
        val resolver = context.contentResolver

        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(fileName)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)

        val contentValues = ContentValues().apply {
            put(MediaStore.Files.FileColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, mimeType ?: "application/octet-stream")
            put(
                MediaStore.Files.FileColumns.RELATIVE_PATH,
                "${Environment.DIRECTORY_DOWNLOADS}/changer"
            )
        }

        return resolver.insert(MediaStore.Files.getContentUri("external"), contentValues)
    }

    fun writeToFile(
        context: Context,
        inputStream: InputStream,
        uri: Uri
    ): Boolean {
        var isWritten = false

        uri.let { it ->
            context.contentResolver.openOutputStream(it)?.use { outputStream ->
                val buffer = ByteArray(1024)
                var len: Int
                while (inputStream.read(buffer).also { len = it } != -1) {
                    outputStream.write(buffer, 0, len)
                }
                isWritten = true
            }
        }

        Log.d("Arquivo", "Arquivo criado? $isWritten")
        return isWritten
    }
}