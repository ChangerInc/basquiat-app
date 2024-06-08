package com.changer.basquiat.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IConversionRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.presentation.ui.conversion.ConversionScreenState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MultipartBody

class ConversionViewModel(
    private val repository: IConversionRepository,
    userPreferences: UserPreferences,
    private val aboutFile: AboutFile
) : ViewModel() {

    private val _authToken: Flow<UsuarioToken?> = userPreferences.authToken
    val authToken: Flow<UsuarioToken?>
        get() = _authToken
    var showDialog: MutableLiveData<Boolean> = MutableLiveData(false)
        private set
    var state = MutableLiveData<ConversionScreenState>(ConversionScreenState.Loading(false, ""))
        private set
    private var fileName: String = ""

    fun enviarArquivo(file: MultipartBody.Part) {
        state.value = ConversionScreenState.Loading(true, "Enviando arquivo...")
        viewModelScope.launch {
            val response = repository.enviarArquivo(file)
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    state.value = ConversionScreenState.Success("Arquivo enviado com sucesso")
                    Log.d("Sucesso", response.body().toString())
                    delay(200)
                    state.value = ConversionScreenState.Loading(false, "Enviando arquivo...")
                    showDialog.value = true
                } else {
                    Log.e("Erro", response.toString())
                }
            }
        }
    }

    fun converterArquivo(context: Context, extensao: String) {
        showDialog.value = false
        state.value = ConversionScreenState.Loading(true, "Realizando conversão...")
        viewModelScope.launch {
            val response = repository.converterArquivo(extensao.lowercase())
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    Log.d("Sucesso", response.body().toString())
                    delay(400)
                    obterUrl(context)
                } else {
                    Log.e("Erro", "Erro ao converter arquivo")
                }
            }

        }
    }

    private fun obterUrl(context: Context) {
        state.value = ConversionScreenState.Loading(true, "Obtendo nome...")
        viewModelScope.launch {
            val response = repository.obterUrl()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    fileName = response.body()!!.string()
                    Log.d("Sucesso", fileName)
                    delay(400)
                    baixarArquivo(context)
                } else {
                    Log.e("Erro", response.body().toString())
                }
            }
        }
    }

    private fun baixarArquivo(context: Context) {
        state.value = ConversionScreenState.Loading(true, "Baixando arquivo...")
        viewModelScope.launch {
            val response = repository.baixarArquivo()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    response.body()?.byteStream()?.let {
                        val uri = aboutFile.createFile(context, fileName, false)
                        if (uri != null) {
                            aboutFile.writeToFile(context, it, uri)
                            tryAgain()
                            Log.d("Sucesso", "Arquivo baixado com sucesso")
                            delay(200)
                            state.value =
                                ConversionScreenState.Success("Arquivo baixado com sucesso")
                            delay(200)
                            tryAgain()
                        }
                    }
                } else {
                    Log.e("Erro", response.body().toString())
                }
            }
        }
    }

    fun tryAgain() {
        state.value = ConversionScreenState.Loading(false, "")
    }
}