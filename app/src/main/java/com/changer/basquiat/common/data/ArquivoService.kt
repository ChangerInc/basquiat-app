package com.changer.basquiat.common.data

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import java.util.UUID

interface ArquivoService {
    @GET("/arquivo/{idUser}")
    suspend fun getAllById(@Path("idUser") idUser: UUID): Response<List<Arquivo>>
}