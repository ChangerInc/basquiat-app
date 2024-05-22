package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.UsuarioService
import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.login.domain.UsuarioToken
import com.changer.basquiat.ui.register.data.Usuario
import retrofit2.Response

class UsuarioRepositoryImp : IUsuarioRepository {
    val api by lazy {
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