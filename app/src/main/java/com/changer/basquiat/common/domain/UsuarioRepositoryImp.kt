package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.remote.ApiConfig
import com.changer.basquiat.common.data.remote.UsuarioService
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.domain.model.UserObj
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.domain.model.Convites
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

class UsuarioRepositoryImp : IUsuarioRepository {
    private val api by lazy {
        ApiConfig
            .getIntance()
            .create(UsuarioService::class.java)
    }

    override suspend fun getUser(login: UserForm): Response<UsuarioToken?> {
        return api.login(login)
    }

    override suspend fun registerUser(user: RegisterForm): Response<UserObj?> {
        return api.register(user)
    }

    override suspend fun getQtdNotificacoes(email: String?): Response<Int> {
        return api.getQtdNotificacoes(email)
    }

    override suspend fun getConvites(email: String?): Response<List<Convites>> {
        return api.getConvites(email)
    }

    override suspend fun patchFoto(idUsuario: UUID?, file: MultipartBody.Part): Response<ResponseBody> {
        return api.patchFoto(idUsuario, file)
    }
}