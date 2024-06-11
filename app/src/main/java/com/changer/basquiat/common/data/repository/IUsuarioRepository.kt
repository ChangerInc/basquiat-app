package com.changer.basquiat.common.data.repository

import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.domain.model.UserObj
import com.changer.basquiat.domain.model.UsuarioToken
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import java.util.UUID

interface IUsuarioRepository {
    suspend fun getUser(login: UserForm): Response<UsuarioToken?>

    suspend fun registerUser(user: RegisterForm): Response<UserObj?>
    suspend fun getQtdNotificacoes(email: String?): Response<Int>
    suspend fun getConvites(email: String?): Response<List<Convites>>
    suspend fun patchFoto(idUsuario: UUID?, file: MultipartBody.Part): Response<ResponseBody>
}