package com.changer.basquiat.common.data.remote

import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.domain.model.UserObj
import com.changer.basquiat.domain.model.UsuarioToken
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Multipart
import retrofit2.http.PATCH
import retrofit2.http.POST
import retrofit2.http.Part
import retrofit2.http.Path
import java.util.UUID

interface UsuarioService {
    @POST("usuario/login")
    suspend fun login(@Body login: UserForm): Response<UsuarioToken?>

    @POST("usuario/")
    suspend fun register(@Body form: RegisterForm): Response<UserObj?>

    @GET("usuario/notificacoes/{email}")
    suspend fun getQtdNotificacoes(@Path("email") email: String?): Response<Int>

    @GET("usuario/convites/{email}")
    suspend fun getConvites(@Path("email") email: String?): Response<List<Convites>>

    @Multipart
    @PATCH("usuario/foto/{idUsuario}")
    suspend fun patchFoto(
        @Path("idUsuario") idUsuario: UUID?,
        @Part file: MultipartBody.Part
    ): Response<ResponseBody>

    /*@PUT("/usuario/{uuid}")
    suspend fun mudarSenha(@Path("uuid") uuid: UUID): Response<Usuario>*/

    /*@GET("/usuario/{codigo}")
    suspend fun getFoto(@Path(value = "codigo") codigo: UUID): Response*/
}
