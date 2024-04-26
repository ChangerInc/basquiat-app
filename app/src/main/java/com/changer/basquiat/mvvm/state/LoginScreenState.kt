package com.changer.basquiat.mvvm.state

import com.changer.basquiat.mvvm.model.Usuario

sealed interface LoginScreenState {
    data object Loading: LoginScreenState

    data class Success(
        val data: Usuario
    ): LoginScreenState

    data class Error(
        val message: String
    ): LoginScreenState
}