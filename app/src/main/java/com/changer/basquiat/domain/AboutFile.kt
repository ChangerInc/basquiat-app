package com.changer.basquiat.domain

import android.content.ContentValues
import android.content.Context
import android.net.Uri
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.webkit.MimeTypeMap
import java.io.InputStream

class AboutFile {
    fun createFile(
        context: Context,
        fileName: String,
        isHistoryFile: Boolean
    ): Uri? {
        val resolver = context.contentResolver

        val fileExtension = MimeTypeMap.getFileExtensionFromUrl(fileName)
        val mimeType = MimeTypeMap.getSingleton().getMimeTypeFromExtension(fileExtension)

        val path = if (isHistoryFile) {
            "${Environment.DIRECTORY_DOWNLOADS}/CHANGER/Histórico"
        } else {
            "${Environment.DIRECTORY_DOWNLOADS}/CHANGER/Conversões"
        }

        val contentValues = ContentValues().apply {
            put(MediaStore.Files.FileColumns.DISPLAY_NAME, fileName)
            put(MediaStore.Files.FileColumns.MIME_TYPE, mimeType ?: "application/octet-stream")
            put(MediaStore.Files.FileColumns.RELATIVE_PATH, path)
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