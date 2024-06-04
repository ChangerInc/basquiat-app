package com.changer.basquiat.common.data.repository

import okhttp3.MultipartBody
import retrofit2.Response
import java.util.UUID

interface IConversionRepository {
    suspend fun enviarArquivo(file: MultipartBody.Part, user: UUID?): Response<String>
    suspend fun converterArquivo(extensao: String): Response<String>
    suspend fun obterUrl(): Response<String>
    suspend fun baixarArquivo(): Response<ByteArray>
}