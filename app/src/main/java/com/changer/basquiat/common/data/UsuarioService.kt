package com.changer.basquiat.common.data

import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.register.data.Usuario
import com.changer.basquiat.ui.login.data.UsuarioToken
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UsuarioService {
    @POST("usuario/login")
    suspend fun login(@Body login: UserForm): Response<UsuarioToken?>

    @POST("usuario/")
    suspend fun cadstro(@Body form: Usuario): Response<Usuario>

    /*@PUT("/usuario/{uuid}")
    suspend fun mudarSenha(@Path("uuid") uuid: UUID): Response<Usuario>*/

    /*@GET("/usuario/{codigo}")
    suspend fun getFoto(@Path(value = "codigo") codigo: UUID): Response*/

}
