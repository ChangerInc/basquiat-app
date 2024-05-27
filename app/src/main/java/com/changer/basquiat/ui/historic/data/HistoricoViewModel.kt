package com.changer.basquiat.ui.historic.data

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.Arquivo
import com.changer.basquiat.common.data.UserPreferences
import com.changer.basquiat.common.domain.IArquivoRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import okhttp3.ResponseBody
import retrofit2.Response
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