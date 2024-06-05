package com.changer.basquiat.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.repository.IConversionRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.presentation.ui.conversion.ConversionScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class ConversionViewModel(
    private val repository: IConversionRepository,
    private val aboutFile: AboutFile
) : ViewModel() {
    var showDialog: MutableLiveData<Boolean> = MutableLiveData(false)
        private set
    var state = MutableLiveData<ConversionScreenState>(ConversionScreenState.Loading(true))
        private set

    fun enviarArquivo(file: MultipartBody.Part) {
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            val response = repository.enviarArquivo(file)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Success("Arquivo enviado com sucesso")
                    Log.d("Sucesso", response.body().toString())
                    delay(200)
                    state.value = ConversionScreenState.Loading(false)
                    showDialog.value = true
                } else {
                    Log.e("Erro", response.toString())
                }
            }
        }
    }

    fun converterArquivo(extensao: String) {
        showDialog.value = false
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            val response = repository.converterArquivo(extensao.lowercase())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Success("Arquivo enviado com sucesso")
                    Log.d("Sucesso", response.body().toString())
                    delay(400)
                    obterUrl()
                } else {
                    Log.e("Erro", "Erro ao converter arquivo")
                }
            }

        }
    }

    private fun obterUrl() {
        viewModelScope.launch {
            val response = repository.obterUrl()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Loading(false)
                    Log.e("Success", response.body().toString())
                } else {
                    Log.e("Erro", response.body().toString())
                }
            }
        }
    }

    fun baixarArquivo() {
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            val response = repository.baixarArquivo()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Success("Arquivo baixado com sucesso")
                    delay(200)
                    state.value = ConversionScreenState.Loading(false)
                } else {
                    Log.e("Erro", response.body().toString())
                }
            }
        }
    }
}