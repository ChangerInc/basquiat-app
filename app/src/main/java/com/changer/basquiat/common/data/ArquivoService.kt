package com.changer.basquiat.common.data

import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.UUID

interface ArquivoService {
    @GET("/arquivo/{idUser}")
    suspend fun getAllById(@Path("idUser") idUser: UUID?): Response<List<Arquivo>>

    @Multipart
    @POST("/arquivo/{idUsuario}")
    suspend fun uploadArquivo(
        @Path("idUsuario") idUsuario: UUID?,
        @Part file: MultipartBody.Part
    ): Response<ResponseBody>
}