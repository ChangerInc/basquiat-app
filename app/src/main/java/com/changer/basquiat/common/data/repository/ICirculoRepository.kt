package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.Circulo
import retrofit2.Response
import retrofit2.http.Path
import java.util.UUID

interface ICirculoRepository {
    suspend fun getCirculos(id: UUID?): Response<List<Circulo>>?

    suspend fun postCirculo(circulo: Circulo): Response<Circulo>

    suspend fun deleteCirculo(circulo: Map<String, UUID>): Response<Circulo>

    suspend fun acaoConvite(
        idCirculo: UUID,
        idUser: UUID,
        acaoBotao: Int
    ): Response<Boolean>

    suspend fun convidarMembro(
        idCirculo: UUID,
        idAnfitriao: UUID,
        emailDoConvidado: String
    ): Response<Boolean>

    suspend fun sairDoCirculo(idCirculo: UUID, idUsuario: UUID): Response<Boolean>

    suspend fun limparMembros(idCirculo: UUID): Response<Boolean>

}