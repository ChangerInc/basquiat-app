package com.changer.basquiat.common.data

import com.changer.basquiat.ui.home.data.Usuario
import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response
import retrofit2.http.POST

interface UsuarioService {
    @POST("/usuario/login")
    suspend fun login(): Response<UsuarioToken>

    @POST("/usuario/")
    suspend fun cadstro(): Response<Usuario>
}