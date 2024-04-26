package com.changer.basquiat.mvvm

import android.net.http.HttpException
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.mvvm.state.LoginScreenState
import com.changer.basquiat.config.IUsuarioRepository
import kotlinx.coroutines.launch

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
                        data = response.body() ?:
                    )
                } else {
                    throw Exception("Erro desconhecido")
                }
            }
        } catch (e: HttpException) {
            val message = when (e.code()) {
                400 -> ""
            }
        }
    }
}