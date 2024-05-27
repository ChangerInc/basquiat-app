package com.changer.basquiat.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.common.data.remote.ArquivoService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.UUID

class HistoricoViewModel: ViewModel() {
    private val api = Retrofit.Builder()
        .baseUrl("http://192.168.1.107:8080/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val service = api.create(ArquivoService::class.java)

    private val _apiResponse = MutableLiveData<List<Arquivo>?>()
    val apiResponse: LiveData<List<Arquivo>?> = _apiResponse

    fun getArquivos(idUser: UUID) {
        viewModelScope.launch(Dispatchers.IO) {
            val response = service.getAllById(idUser)
            val body = response.body()
            _apiResponse.postValue(body)
        }
    }
}