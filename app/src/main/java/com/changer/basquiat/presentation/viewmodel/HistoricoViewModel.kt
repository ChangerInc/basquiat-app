package com.changer.basquiat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IArquivoRepository
import com.changer.basquiat.domain.model.Arquivo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.util.UUID

class HistoricoViewModel(
    private val repository: IArquivoRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {

    var arquivos = MutableLiveData<List<Arquivo>>()
        private set

    fun uploadArquivo(idUsuario: UUID?, file: MultipartBody.Part) {
        try {
            viewModelScope.launch {
                withContext(Dispatchers.IO) {
                    val response = repository.uploadArquivo(idUsuario, file)
                    if (response.isSuccessful) {
                        getArquivos(idUsuario)
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getArquivos(idUser: UUID?) {
        try {
            viewModelScope.launch {
                val response = repository.getArquivos(idUser)
                if (response?.isSuccessful == true) {
                    arquivos.value = response.body()
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}