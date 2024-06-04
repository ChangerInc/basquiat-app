package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.remote.ApiConfig
import com.changer.basquiat.common.data.remote.ConversionService
import com.changer.basquiat.common.data.repository.IConversionRepository
import okhttp3.MultipartBody
import java.util.UUID

class ConversionRepositoryImp : IConversionRepository {
    private val api by lazy {
        ApiConfig
            .getIntance()
            .create(ConversionService::class.java)
    }

    override suspend fun enviarArquivo(file: MultipartBody.Part, user: UUID?) =
        api.enviarArquivo(file, user)

    override suspend fun converterArquivo(extensao: String) =
        api.converterArquivo(extensao)

    override suspend fun obterUrl() = api.obterUrl()

    override suspend fun baixarArquivo() = api.baixarArquivo()
}