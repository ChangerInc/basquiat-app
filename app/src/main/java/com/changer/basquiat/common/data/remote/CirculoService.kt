package com.changer.basquiat.common.data.remote

import com.changer.basquiat.domain.model.Circulo
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.UUID

interface CirculoService {
    @GET("/circulo/todos/{idUser}")
    suspend fun getCirculos(@Path("idUser") id: UUID?): Response<List<Circulo>>?

    @POST("/circulo/")
    suspend fun postCirculo(@Body circulo: Circulo): Response<Circulo>

    @DELETE("/circulo/")
    suspend fun deleteCirculo(@Body circulo: Map<String, UUID>): Response<Circulo>

    suspend fun acaoConvite(
        @Query("idCirculo") idCirculo: UUID,
        @Query("idUser") idUser: UUID,
        @Path("acaoBotao") acaoBotao: Int
    ): Response<Boolean>

    @POST("/circulo/convidar/{idCirculo}")
    suspend fun convidarMembro(
        @Path("idCirculo") idCirculo: UUID,
        @Query("idAnfitriao") idAnfitriao: UUID,
        @Query("emailDoConvidado") emailDoConvidado: String
    ): Response<Boolean>

    @PATCH("/circulo/sair/{idCirculo}/{idUsuario}")
    suspend fun sairDoCirculo(
        @Path("idCirculo") idCirculo: UUID,
        @Path("idUsuario") idUsuario: UUID
    ): Response<Boolean>

    @DELETE("/circulo/limpar/{idCirculo}")
    suspend fun limparMembros(
        @Path("idCirculo") idCirculo: UUID
    ): Response<Boolean>
}