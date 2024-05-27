package com.changer.basquiat.domain.repository

import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.Usuario
import com.changer.basquiat.domain.model.UsuarioToken
import retrofit2.Response

interface IUsuarioRepository {
    suspend fun getUser(login: UserForm): Response<UsuarioToken?>

    suspend fun registerUser(user: Usuario): Response<Usuario>
}