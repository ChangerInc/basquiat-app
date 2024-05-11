package com.changer.basquiat.ui.login.domain

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.domain.IUsuarioRepository
import com.changer.basquiat.ui.login.data.UserForm
import com.changer.basquiat.ui.login.data.UsuarioToken
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (
    private val repository: IUsuarioRepository
): ViewModel() {
    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(login: UserForm) {
        try {
            viewModelScope.launch {
                val response = repository.getUser(login)
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
                400 -> "Informações faltando"
                404 -> "Email ou senha incorretos"
                else -> "Erro desconhecido"
            }

            state.value = LoginScreenState.Error(message)
        }
    }
}