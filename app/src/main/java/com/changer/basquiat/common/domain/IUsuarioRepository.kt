package com.changer.basquiat.common.domain.domain

import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response

interface IUsuarioRepository {
    suspend fun getUser(): Response<UsuarioToken>

    suspend fun registerUser(user: UsuarioToken): Response<Int>
}