package com.changer.basquiat.presentation.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.authentication.FirebaseAuthRepository
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IArquivoRepository
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.presentation.ui.historic.HistoricScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.MultipartBody
import java.util.UUID


class HistoricoViewModel(
    private val repository: IArquivoRepository,
    private val repositoryUser: IUsuarioRepository,
    private val firebaseAuthRepository: FirebaseAuthRepository,
    private val userPreferences: UserPreferences,
    private var aboutFile: AboutFile
) : ViewModel() {

    var state = MutableLiveData<HistoricScreenState>(HistoricScreenState.Loading(false))
        private set
    var arquivos = MutableLiveData<List<Arquivo>>()
        private set
    private val _authToken: Flow<UsuarioToken?> = userPreferences.authToken
    val authToken: Flow<UsuarioToken?>
        get() = _authToken
    var countNotifications = MutableLiveData<Int>()
    var convites = MutableLiveData<List<Convites>>()
        private set

    fun uploadArquivo(file: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                state.value = HistoricScreenState.Loading(true)
                authToken.collect {
                    val idUser = it?.getId()
                    val response = repository.uploadArquivo(idUser, file)
                    if (response.isSuccessful) {
                        state.value = HistoricScreenState.Success("Arquivo enviado com sucesso")
                        delay(200)
                        getArquivos()
                    } else {
                        state.value = HistoricScreenState.Error("Erro ao enviar arquivo")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getArquivos() {
        viewModelScope.launch {
            try {
                state.value = HistoricScreenState.Loading(true)
                authToken.collect { token ->
                    val idUser = token?.getId()
                    val response = repository.getArquivos(idUser)
                    if (response?.isSuccessful == true) {
                        arquivos.value = response.body()?.reversed()
                        state.value = HistoricScreenState.Loading(false)
                    } else {
                        state.value = HistoricScreenState.Loading(false)
                        delay(200)
                        state.value = HistoricScreenState.Error("Erro ao carregar arquivos")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun downloadArquivo(context: Context, idArquivo: UUID, fileName: String) {
        viewModelScope.launch {
            try {
                state.value = HistoricScreenState.Loading(true)
                authToken.collect { token ->
                    val idUser = token?.getId()
                    val response = repository.downloadArquivo(idUser, idArquivo)
                    if (response.isSuccessful) {
                        response.body()?.byteStream()?.let { inputStream ->
                            val uri = aboutFile.createFile(context, fileName, true)
                            if (uri != null) {
                                aboutFile.writeToFile(context, inputStream, uri)
                                state.value =
                                    HistoricScreenState.Success("Arquivo baixado com sucesso")
                                delay(200)
                                state.value = HistoricScreenState.Loading(false)
                            }
                        }
                    } else {
                        state.value = HistoricScreenState.Loading(false)
                        delay(200)
                        state.value = HistoricScreenState.Error("Erro ao baixar arquivo")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteArquivo(idArquivo: UUID) {
        viewModelScope.launch {
            try {
                state.value = HistoricScreenState.Loading(true)
                authToken.collect { token ->
                    val idUser = token?.getId()
                    val response = repository.deleteArquivo(idUser, idArquivo)
                    if (response.isSuccessful) {
                        state.value =
                            HistoricScreenState.Success("Arquivo excluido com sucesso")
                        getArquivos()
                    } else {
                        state.value = HistoricScreenState.Loading(false)
                        delay(200)
                        state.value =
                            HistoricScreenState.Error("Não foi possível excluir o arquivo")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getQtdNotificacoes() {
        viewModelScope.launch {
            try {
                authToken.collect { token ->
                    val emailUser = token?.getEmail()
                    val response = repositoryUser.getQtdNotificacoes(emailUser)
                    if (response.isSuccessful) {
                        countNotifications.value = response.body()
                    } else {
                        state.value =
                            HistoricScreenState.Error("Erro ao carregar quantidade de notificações")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun getConvites() {
        viewModelScope.launch {
            try {
                authToken.collect { token ->
                    val email = token?.getEmail()
                    val response = repositoryUser.getConvites(email)
                    if (response.isSuccessful) {
                        convites.value = response.body()
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun patchFoto(file: MultipartBody.Part) {
        viewModelScope.launch {
            try {
                state.value = HistoricScreenState.Loading(true)
                authToken.collect { token ->
                    val idUsuario = token?.getId()
                    val response = repositoryUser.patchFoto(idUsuario, file)
                    if (response.isSuccessful) {
                        val newPhotoUrl = response.body()?.string()?.replace("'", "") ?: ""
                        userPreferences.updateProfilePhoto(newPhotoUrl)
                        state.value = HistoricScreenState.Success("Foto alterada com sucesso")
                        delay(200)
                        tryAgain()
                    } else {
                        tryAgain()
                        delay(200)
                        state.value = HistoricScreenState.Error("Erro ao alterar foto")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun signOut() {
        viewModelScope.launch {
            try {
                firebaseAuthRepository.signOut()
            } catch (e: Exception) {
                state.value = HistoricScreenState.Error("Erro ao sair")
            }
        }
    }

    fun tryAgain() {
        state.value = HistoricScreenState.Loading(false)
    }
}