package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.domain.model.UserObj
import com.changer.basquiat.domain.model.UsuarioToken
import retrofit2.Response

interface IUsuarioRepository {
    suspend fun getUser(login: UserForm): Response<UsuarioToken?>

    suspend fun registerUser(user: RegisterForm): Response<UserObj?>
}