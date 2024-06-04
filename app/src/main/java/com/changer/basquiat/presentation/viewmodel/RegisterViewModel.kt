package com.changer.basquiat.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.changer.basquiat.common.data.repository.IUsuarioRepository
import com.changer.basquiat.domain.model.RegisterForm
import com.changer.basquiat.presentation.ui.register.RegisterScreenState
import com.changer.basquiat.presentation.ui.theme.Azul
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.HttpException

class RegisterViewModel(
    private val repository: IUsuarioRepository,
    private val loginVm: LoginViewModel
) : ViewModel() {
    private val _name = MutableStateFlow("")
    val name: StateFlow<String> = _name

    private val _email = MutableStateFlow("")
    val email: StateFlow<String> = _email

    private val _password = MutableStateFlow("")
    val password: StateFlow<String> = _password

    private val _passwordConfirm = MutableStateFlow("")
    val passwordConfirm: StateFlow<String> = _passwordConfirm


    private val _nameColor = MutableStateFlow(Azul)
    val nameColor: StateFlow<Color> = _nameColor

    private val _emailColor = MutableStateFlow(Azul)
    val emailColor: StateFlow<Color> = _emailColor

    private val _passwordColor = MutableStateFlow(Azul)
    val passwordColor: StateFlow<Color> = _passwordColor

    private val _passwordConfirmColor = MutableStateFlow(Azul)
    val passwordConfirmColor: StateFlow<Color> = _passwordConfirmColor

    fun validateName(name: String): Boolean {
        _name.value = name
        return if (name.length in 3..40 && _name.value != "") {
            _nameColor.value = Azul
            true
        } else {
            _nameColor.value = Color.Red
            false
        }
    }

    fun validateEmail(email: String): Boolean {
        _email.value = email
        return if (android.util.Patterns.EMAIL_ADDRESS.matcher(email)
                .matches() && _email.value != ""
        ) {
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

    fun validatePasswordConfirm(passwordConfirm: String): Boolean {
        _passwordConfirm.value = passwordConfirm
        return if (passwordConfirm == _password.value && _passwordConfirm.value != "") {
            _passwordConfirmColor.value = Azul
            true
        } else {
            _passwordConfirmColor.value = Color.Red
            false
        }
    }

    var state = MutableLiveData<RegisterScreenState>(RegisterScreenState.Normalize)
        private set

    fun registerSend() {
        val form = RegisterForm(
            _name.value,
            _email.value,
            _password.value
        )

        viewModelScope.launch {
            try {
                state.value = RegisterScreenState.Loading

                delay(400)

                val response = repository.registerUser(form)

                if (response.isSuccessful) {
                    response.body()?.let {
                        state.value = RegisterScreenState.Success(data = it)
                    }
                } else {
                    val message = when (response.code()) {
                        409 -> "Email inválido"
                        else -> response.toString()
                    }
                    state.value = RegisterScreenState.Error(message)
                }
            } catch (e: HttpException) {
                state.value = RegisterScreenState.Error(e.message.toString())
            } catch (e: Exception) {
                state.value = RegisterScreenState.Error(e.message.toString())
            }
        }
    }

    fun TryAgain() {
        state.value = RegisterScreenState.Normalize
    }
}