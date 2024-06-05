package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.Circulo
import retrofit2.Response
import java.util.UUID

interface ICirculoRepository {
    suspend fun getCirculos(id: UUID): Response<List<Circulo>>

    suspend fun postCirculo(circulo: Circulo): Response<Circulo>
}