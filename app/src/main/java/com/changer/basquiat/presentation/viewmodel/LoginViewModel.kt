package com.changer.basquiat.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.domain.repository.IUsuarioRepository
import com.changer.basquiat.presentation.ui.login.LoginScreenState
import com.changer.basquiat.presentation.ui.theme.Azul
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel (
    private val repository: IUsuarioRepository,
    private val userPreferences: UserPreferences
): ViewModel() {
    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password


    private val _emailColor = MutableStateFlow(Azul)
    val emailColor: StateFlow<Color> = _emailColor

    private val _passwordColor = MutableStateFlow(Azul)
    val passwordColor: StateFlow<Color> = _passwordColor

    fun validateEmail(email: String): Boolean {
        _email.value = email
        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches() && _email.value != "") {
            _emailColor.value = Azul
            true
        } else {
            _emailColor.value = Color.Red
            false
        }
    }

    fun validatePassword(password: String): Boolean {
        _password.value = password
        return if (password.length in 6..20 && _password.value != "") {
            _passwordColor.value = Azul
            true
        } else {
            _passwordColor.value = Color.Red
            false
        }
    }

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