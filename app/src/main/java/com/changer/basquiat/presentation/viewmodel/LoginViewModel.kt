package com.changer.basquiat.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.preferences.UserPreferences
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.domain.model.UserForm
import com.changer.basquiat.presentation.ui.login.LoginScreenState
import com.changer.basquiat.presentation.ui.theme.Azul
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.delay
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

    var state = MutableLiveData<LoginScreenState>(LoginScreenState.Normalize)
        private set

    fun getUser(form: UserForm) {
        viewModelScope.launch {
            try {
                state.value = LoginScreenState.Loading
                delay(400)
                val response = repository.getUser(form)
                if (response.isSuccessful) {
                    response.body()?.let {
                        userPreferences.saveAuthToken(it)
                        state.value = LoginScreenState.Success(data = it)
                    }
                } else {
                    val message = when (response.code()) {
                        400 -> "Email ou senha incorretos"
                        401 -> "E-mail ou senha incorretos"
                        404 -> "Email ou senha incorretos"
                        405 -> "Método http não permitido"
                        500 -> "Erro interno do servidor"
                        else -> "Erro desconhecido"
                    }
                    state.value = LoginScreenState.Error(message)
                }
            } catch (e: HttpException) {
                state.value = LoginScreenState.Error(e.message.toString())
            } catch (e: Exception) {
                state.value = LoginScreenState.Error(e.message.toString())
            }
        }
    }

    fun tryAgain() {
        state.value = LoginScreenState.Normalize
    }

}

