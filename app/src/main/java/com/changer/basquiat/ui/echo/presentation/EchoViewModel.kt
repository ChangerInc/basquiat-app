package com.changer.basquiat.ui.echo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.ui.echo.data.EchoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class EchoViewModel : ViewModel() {
    private val retrofit = Retrofit.Builder()
        .baseUrl("http://10.18.36.135:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = retrofit.create(EchoService::class.java)

    private val _echoResponse = MutableLiveData<String>()
    val echoResponse: LiveData<String> = _echoResponse

    fun testEcho() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.echo()
            val body = response.string() // Lê o corpo da resposta como uma string
            _echoResponse.postValue(body)
        }
    }
}