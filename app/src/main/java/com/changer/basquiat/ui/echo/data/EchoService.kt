package com.changer.basquiat.ui.echo.data

import okhttp3.ResponseBody
import retrofit2.http.GET

interface EchoService {
    @GET("changer/echo")
    suspend fun echo(): ResponseBody
}