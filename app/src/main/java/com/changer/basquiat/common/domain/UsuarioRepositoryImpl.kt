package com.changer.basquiat.common.domain

import com.changer.basquiat.common.domain.domain.IUsuarioRepository
import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response

class UsuarioRepositoryImpl: IUsuarioRepository {
    override suspend fun getUser(): Response<UsuarioToken> {
        TODO("Not yet implemented")
    }

    override suspend fun registerUser(user: UsuarioToken): Response<Int> {
        TODO("Not yet implemented")
    }
}