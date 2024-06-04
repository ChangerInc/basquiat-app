package com.changer.basquiat.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IConversionRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.presentation.ui.conversion.ConversionScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody

class ConversionViewModel(
    private val repository: IConversionRepository,
    userPreferences: UserPreferences,
    private val aboutFile: AboutFile
) : ViewModel() {
    var showDialog: MutableLiveData<Boolean> = MutableLiveData(false)
        private set
    private val _authToken: Flow<UsuarioToken?> = userPreferences.authToken
    var state = MutableLiveData<ConversionScreenState>(ConversionScreenState.Loading(true))
        private set

    fun enviarArquivo(file: MultipartBody.Part) {
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            _authToken.collect { token ->
                val idUser = token?.getId()
                val response = repository.enviarArquivo(file, idUser)
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Success("Arquivo enviado com sucesso")
                    delay(200)
                    state.value = ConversionScreenState.Loading(false)
                    showDialog.value = true
                } else {
                    Log.e("Erro", "Erro ao enviar arquivo")
                }
            }
        }
    }

    fun converterArquivo(extensao: String) {
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            val response = repository.converterArquivo(extensao)
            if (response.isSuccessful) {
                state.value = ConversionScreenState.Success("Arquivo enviado com sucesso")
                delay(200)
            } else {
                Log.e("Erro", "Erro ao converter arquivo")
            }
            obterUrl()
        }
    }

    fun obterUrl() {
        viewModelScope.launch {
            val response = repository.obterUrl()
            if (response.isSuccessful) {
                state.value = ConversionScreenState.Loading(false)
            } else {
                Log.e("Erro", "Erro ao obter url do arquivo")
            }
        }
    }

    fun baixarArquivo() {
        state.value = ConversionScreenState.Loading(true)
        viewModelScope.launch {
            val response = repository.baixarArquivo()
            if (response.isSuccessful) {
                state.value = ConversionScreenState.Success("Arquivo baixado com sucesso")
                delay(200)
                state.value = ConversionScreenState.Loading(false)
            } else {
                Log.e("Erro", "Erro ao baixar o arquivo")
            }
        }
    }
}