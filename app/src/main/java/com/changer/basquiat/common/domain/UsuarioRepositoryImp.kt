package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.remote.ApiConfig
import com.changer.basquiat.common.data.remote.UsuarioService
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.Usuario
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import retrofit2.Response

class UsuarioRepositoryImp : IUsuarioRepository {
    private val api by lazy {
        ApiConfig
            .getIntance()
            .create(UsuarioService::class.java)
    }

    override suspend fun getUser(login: UserForm): Response<UsuarioToken?> {
        return api.login(login)
    }

    override suspend fun registerUser(user: Usuario): Response<Usuario> {
        return api.cadstro(user)
    }
}