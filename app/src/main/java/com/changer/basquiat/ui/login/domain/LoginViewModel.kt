package com.changer.basquiat.ui.login.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.ui.login.domain.LoginScreenState
import com.changer.basquiat.common.domain.domain.IUsuarioRepository
import com.changer.basquiat.ui.login.data.UsuarioToken
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (
    private val repository: IUsuarioRepository
): ViewModel() {
    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(login: Map<String, String>) {
        login.size
        try {
            viewModelScope.launch {
                val response = repository.getUser()
                state.value = LoginScreenState.Loading

                if (response.isSuccessful) {
                    state.value = LoginScreenState.Success(
                        data = response.body()
                    )
                } else {
                    throw Exception("Erro desconhecido")
                }
            }
        } catch (e: HttpException) {
            val message = when (e.code()) {
                404 -> "Email de usuário não encontrado"
                else -> "Erro desconhecido"
            }

            state.value = LoginScreenState.Error(message)
        }
    }
}