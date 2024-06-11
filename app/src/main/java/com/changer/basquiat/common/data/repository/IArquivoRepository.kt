package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.Arquivo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

interface IArquivoRepository {
    suspend fun getArquivos(id: UUID?): Response<List<Arquivo>>?
    suspend fun uploadArquivo(idUsuario: UUID?, file: MultipartBody.Part): Response<ResponseBody>
    suspend fun downloadArquivo(idUsuario: UUID?, idArquivo: UUID?): Response<ResponseBody>
    suspend fun deleteArquivo(idUsuario: UUID?, idArquivo: UUID?): Response<ResponseBody>

    suspend fun uploadArquivoCircle(
        idCirculo: UUID,
        file: MultipartBody.Part
    ): Response<ResponseBody>
    suspend fun downloadArquivoCircle(
        idCirculo: UUID,
        idArquivo: UUID
    ): Response<ResponseBody>
    suspend fun deleteArquivoCircle(
        idCirculo: UUID,
        idArquivo: UUID
    ): Response<ResponseBody>
}