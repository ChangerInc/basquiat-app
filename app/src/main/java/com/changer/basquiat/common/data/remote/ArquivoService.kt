package com.changer.basquiat.common.data.remote

import com.changer.basquiat.domain.model.Arquivo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import retrofit2.http.Query
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

    @GET("/arquivo/{idUsuario}/{idArquivo}")
    suspend fun downloadArquivo(
        @Path("idUsuario") idUsuario: UUID?,
        @Path("idArquivo") idArquivo: UUID?
    ): Response<ResponseBody>

    @DELETE("/arquivo/{idUsuario}/{idArquivo}")
    suspend fun deleteArquivo(
        @Path("idUsuario") idUsuario: UUID?,
        @Path("idArquivo") idArquivo: UUID?
    ): Response<ResponseBody>

    @PATCH("/arquivo/circulo/{idCirculo}")
    suspend fun uploadArquivoCircle(
        @Path("idCirculo") idCirculo: UUID,
        @Query("file") file: MultipartBody.Part
    ): Response<ResponseBody>

    @GET("/arquivo/circulo/{idCirculo}/{idArquivo}")
    suspend fun downloadArquivoCircle(
        @Path("idCirculo") idCirculo: UUID,
        @Path("idArquivo") idArquivo: UUID
    ): Response<ResponseBody>

    suspend fun deleteArquivoCircle(
        @Path("idCirculo") idCirculo: UUID,
        @Path("idArquivo") idArquivo: UUID
    ): Response<ResponseBody>
}