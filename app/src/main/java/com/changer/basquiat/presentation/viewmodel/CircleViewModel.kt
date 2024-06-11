package com.changer.basquiat.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IArquivoRepository
import com.changer.basquiat.common.data.repository.ICirculoRepository
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.domain.AboutFile
import com.changer.basquiat.domain.model.Arquivo
import com.changer.basquiat.domain.model.Circulo
import com.changer.basquiat.domain.model.Convites
import com.changer.basquiat.domain.model.UsuarioToken
import com.changer.basquiat.presentation.ui.circle.CircleScreenState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import java.util.UUID

class CircleViewModel(
    private val repositoryArquivo: IArquivoRepository,
    private val repositoryUser: IUsuarioRepository,
    private val repositoryCirculo: ICirculoRepository,
    userPreferences: UserPreferences,
    private var aboutFile: AboutFile
) : ViewModel() {
    var state = MutableLiveData<CircleScreenState>(CircleScreenState.Loading(true))
        private set
    private val _authToken: Flow<UsuarioToken?> = userPreferences.authToken
    val authToken: Flow<UsuarioToken?>
        get() = _authToken
    var circulos = MutableLiveData<List<Circulo>>()
        private set
    var arquivos = MutableLiveData<List<Arquivo>>()
        private set
    var countNotifications = MutableLiveData<Int>()
    var convites = MutableLiveData<List<Convites>>()
        private set

    fun getCirculos() {
        viewModelScope.launch {
            try {
                authToken.collect { user ->
                    val response = repositoryCirculo.getCirculos(user?.getId())
                    if (response?.isSuccessful == true) {
                        circulos.value = response.body()
                        state.value = CircleScreenState.Loading(false)
                    } else {
                        state.value = CircleScreenState.Loading(true)
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun postCirculo(nome: String) {
        viewModelScope.launch {
            try {
                authToken.collect { user ->
                    val response = repositoryCirculo.postCirculo(
                        Circulo(
                            id = UUID.randomUUID(),
                            nomeCirculo = nome,
                            dono = user!!,
                            membros = listOf(),
                            arquivos = listOf()
                        )
                    )

                    if (response.isSuccessful) {
                        state.value = CircleScreenState.Success("Circulo criado com sucesso")
                        getCirculos()
                    } else {
                        state.value = CircleScreenState.Loading(false)
                        delay(200)
                        CircleScreenState.Error("Não foi possível criar o circulo")
                    }
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    fun deleteArquivo(idArquivo: UUID) {
        try {
            viewModelScope.launch {
                authToken.collect { user ->
                    val response = repositoryArquivo.deleteArquivo(
                        user?.getId(),
                        idArquivo
                    )
                    if (response.isSuccessful) {
                        state.value = CircleScreenState.Success("Arquivo excluido com sucesso")
                        getCirculos()
                    } else {
                        state.value = CircleScreenState.Loading(false)
                        delay(200)
                        state.value =
                            CircleScreenState.Error("Não foi possível excluir o arquivo")
                    }
                }
            }
        } catch (e: Exception) {
            e.printStackTrace()
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
                            CircleScreenState.Error("Erro ao carregar quantidade de notificações")
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

    fun tryAgain() {
        state.value = CircleScreenState.Loading(false)
    }
}