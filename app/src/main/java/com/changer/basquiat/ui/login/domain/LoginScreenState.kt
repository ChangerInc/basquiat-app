package com.changer.basquiat.ui.login.domain

sealed interface LoginScreenState {
    data object Loading: LoginScreenState

    data class Success(
        val data: UsuarioToken
    ): LoginScreenState

    data class Error(
        val message: String
    ): LoginScreenState
}