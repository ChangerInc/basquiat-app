package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.remote.ApiConfig
import com.changer.basquiat.common.data.remote.CirculoService
import com.changer.basquiat.common.data.repository.ICirculoRepository
import com.changer.basquiat.domain.model.Circulo
import retrofit2.Response
import java.util.UUID

class CirculoRepositoryImp : ICirculoRepository {
    private val api by lazy {
        ApiConfig
            .getIntance()
            .create(CirculoService::class.java)
    }
    
    override suspend fun getCirculos(id: UUID?): Response<List<Circulo>>? {
        return api.getCirculos(id)
    }

    override suspend fun postCirculo(circulo: Circulo): Response<Circulo> {
        return api.postCirculo(circulo)
    }

    override suspend fun deleteCirculo(circulo: Map<String, UUID>): Response<Circulo> {
        return api.deleteCirculo(circulo)
    }

    override suspend fun acaoConvite(
        idCirculo: UUID,
        idUser: UUID,
        acaoBotao: Int
    ): Response<Boolean> {
        return api.acaoConvite(idCirculo, idUser, acaoBotao)
    }

    override suspend fun convidarMembro(
        idCirculo: UUID,
        idAnfitriao: UUID,
        emailDoConvidado: String
    ): Response<Boolean> {
        return api.convidarMembro(idCirculo, idAnfitriao, emailDoConvidado)
    }

    override suspend fun sairDoCirculo(idCirculo: UUID, idUsuario: UUID): Response<Boolean> {
        return api.sairDoCirculo(idCirculo, idUsuario)
    }

    override suspend fun limparMembros(idCirculo: UUID): Response<Boolean> {
        return api.limparMembros(idCirculo)
    }
}