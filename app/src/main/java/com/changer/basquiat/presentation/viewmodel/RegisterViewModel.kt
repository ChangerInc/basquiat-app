package com.changer.basquiat.presentation.viewmodel

import androidx.compose.ui.graphics.Color
import androidx.lifecycle.ViewModel
import com.changer.basquiat.presentation.ui.theme.Azul
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RegisterViewModel : ViewModel() {
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
}