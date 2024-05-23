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

class LoginViewModel (
    private val repository: IUsuarioRepository,
    private val userPreferences: UserPreferences
): ViewModel() {
    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Loading)
        private set

    fun getUser(form: UserForm) {
        try {
            viewModelScope.launch {
                val response = repository.getUser(form)
                state.value = LoginScreenState.Loading

                if (response.isSuccessful) {
                    response.body()?.let {
                        userPreferences.saveAuthToken(it)
                        state.value = LoginScreenState.Success(data = it)
                    } ?: LoginScreenState.Error("Erro desconhecido")
                } else {
                    state.value = LoginScreenState.Error("Erro desconhecido")
                }
            }
        } catch (e: HttpException) {
            val message = when (e.code()) {
                400 -> "Campos em branco"
                404 -> "Email ou senha incorretos"
                else -> "Erro desconhecido"
            }

            state.value = LoginScreenState.Error(message)
        } catch (e: Exception) {
            state.value = LoginScreenState.Error("Erro desconhecido")
        }
    }
}