package com.changer.basquiat.common.domain

import com.changer.basquiat.common.data.UsuarioService
import com.changer.basquiat.ui.register.data.Usuario
import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response

interface UsuarioRepositoryImpl: IUsuarioRepository {
    override suspend fun getUser(): Response<UsuarioToken> {
        val api = ApiConfig
            .getIntance()
            .create(UsuarioService::class.java)
        return api.login()
    }

    override suspend fun registerUser(user: Usuario): Response<Usuario> {
        val api = ApiConfig
            .getIntance()
            .create(UsuarioService::class.java)
        return api.cadstro()
    }
}