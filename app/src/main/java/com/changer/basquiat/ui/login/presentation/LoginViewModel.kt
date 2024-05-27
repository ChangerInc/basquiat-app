package com.changer.basquiat.ui.login.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.UserPreferences
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.login.domain.LoginScreenState
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel(
    private val repository: IUsuarioRepository,
    private val userPreferences: UserPreferences
) : ViewModel() {
    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(form: UserForm) {
        viewModelScope.launch {
            state.value = LoginScreenState.Loading
            try {
                val response = repository.getUser(form)

                if (response.isSuccessful) {
                    response.body()?.let {
                        userPreferences.saveAuthToken(it)
                        state.value = LoginScreenState.Success(data = it)
                    }
                }
            } catch (e: HttpException) {
                val message = when (e.code()) {
                    400 -> "Email ou senha incorretos"
                    401 -> "Não autorizado"
                    404 -> "Email ou senha incorretos"
                    405 -> "Método http não permitido"
                    500 -> "Erro interno do servidor"
                    else -> "Erro desconhecido"
                }

                    state.value = LoginScreenState.Error(message)
            } catch (e: Exception) {
                    state.value = LoginScreenState.Error(e.message.toString())
            }
        }
    }
}