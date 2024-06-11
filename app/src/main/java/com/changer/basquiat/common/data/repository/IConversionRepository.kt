package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.ConversionResponse
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response

interface IConversionRepository {
    suspend fun enviarArquivo(file: MultipartBody.Part): Response<ConversionResponse>
    suspend fun converterArquivo(extensao: String): Response<ConversionResponse>
    suspend fun obterUrl(): Response<ResponseBody>
    suspend fun baixarArquivo(): Response<ResponseBody>
}