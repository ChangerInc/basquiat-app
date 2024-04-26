package com.changer.basquiat.config

import com.changer.basquiat.mvvm.model.Usuario
import retrofit2.Response

interface IUsuarioRepository {
    suspend fun getUser(): Response<Usuario>

    suspend fun registerUser(user: Usuario): Response<Int>
}