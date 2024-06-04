package com.changer.basquiat.common.data.repository

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

interface IConversionRepository {
    suspend fun enviarArquivo(file: MultipartBody.Part, user: UUID?): Response<ResponseBody>
    suspend fun converterArquivo(extensao: String): Response<ResponseBody>
    suspend fun obterUrl(): Response<ResponseBody>
    suspend fun baixarArquivo(): Response<ByteArray>
}