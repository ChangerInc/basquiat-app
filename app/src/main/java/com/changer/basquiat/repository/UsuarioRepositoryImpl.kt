package com.changer.basquiat.repository

import com.changer.basquiat.config.IUsuarioRepository
import com.changer.basquiat.mvvm.model.Usuario
import retrofit2.Response

class UsuarioRepositoryImpl: IUsuarioRepository {
    override suspend fun getUser(): Response<Usuario> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(user: Usuario): Response<Int> {
        TODO("Not yet implemented")
    }
}