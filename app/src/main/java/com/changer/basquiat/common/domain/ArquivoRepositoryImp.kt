package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.remote.ApiConfig
import com.changer.basquiat.common.data.remote.ArquivoService
import com.changer.basquiat.domain.model.Arquivo
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

class ArquivoRepositoryImp : IArquivoRepository {
    val api by lazy {
        ApiConfig
            .getIntance()
            .create(ArquivoService::class.java)
    }

    override suspend fun getArquivos(id: UUID?): Response<List<Arquivo>> {
        return api.getAllById(id)
    }

    override suspend fun uploadArquivo(
        idUsuario: UUID?,
        file: MultipartBody.Part
    ): Response<ResponseBody> {
        return api.uploadArquivo(idUsuario, file)
    }
}
