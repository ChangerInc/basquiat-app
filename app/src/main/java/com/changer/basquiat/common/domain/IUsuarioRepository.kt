package com.changer.basquiat.common.domain

import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.register.data.Usuario
import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response

interface IUsuarioRepository {
    suspend fun getUser(login: UserForm): Response<UsuarioToken>

    suspend fun registerUser(user: Usuario): Response<Usuario>
}