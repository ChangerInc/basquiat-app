package com.changer.basquiat.presentation.viewmodel

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IArquivoRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.UsuarioToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody
import java.util.UUID


class HistoricoViewModel(
    private val repository: IArquivoRepository,
    userPreferences: UserPreferences,
    aboutFile: AboutFile
) : ViewModel() {

    var about = aboutFile
        private set
    var arquivos = MutableLiveData<List<Arquivo>>()
        private set
    private val _authToken: Flow<UsuarioToken?> = userPreferences.authToken
    val authToken: Flow<UsuarioToken?>
        get() = _authToken

    fun uploadArquivo(file: MultipartBody.Part) {
        try {
            viewModelScope.launch {
                authToken.collect {
                    val idUser = it?.getId()
                    withContext(Dispatchers.IO) {
                        val response = repository.uploadArquivo(idUser, file)
                        if (response.isSuccessful) {
                            getArquivos()
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun getArquivos() {
        try {
            viewModelScope.launch {
                authToken.collect { token ->
                    val idUser = token?.getId()
                    val response = repository.getArquivos(idUser)
                    if (response?.isSuccessful == true) {
                        arquivos.value = response.body()
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    fun downloadArquivo(context: Context, idArquivo: UUID, fileName: String) {
        try {
            viewModelScope.launch {
                authToken.collect { token ->
                    val idUser = token?.getId()
                    val response = repository.downloadArquivo(idUser, idArquivo)
                    if (response.isSuccessful) {
                        response.body()?.byteStream()?.let { inputStream ->
                            val uri = about.createFile(context, fileName)
                            if (uri != null) {
                                about.writeToFile(context, inputStream, uri)
                            }
                        }
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}