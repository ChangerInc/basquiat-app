package com.changer.basquiat.common.data.remote

import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.domain.model.UserObj
import com.changer.basquiat.domain.model.UsuarioToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {
    @POST("usuario/login")
    suspend fun login(@Body login: UserForm): Response<UsuarioToken?>

    @POST("usuario/")
    suspend fun register(@Body form: RegisterForm): Response<UserObj?>

    /*@PUT("/usuario/{uuid}")
    suspend fun mudarSenha(@Path("uuid") uuid: UUID): Response<Usuario>*/

    /*@GET("/usuario/{codigo}")
    suspend fun getFoto(@Path(value = "codigo") codigo: UUID): Response*/
}
