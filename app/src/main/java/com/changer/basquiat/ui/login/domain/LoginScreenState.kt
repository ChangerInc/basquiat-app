package com.changer.basquiat.ui.login.domain

import com.changer.basquiat.ui.login.data.UsuarioToken

sealed interface LoginScreenState {
    data object Loading: LoginScreenState

    data class Success(
        val data: UsuarioToken
    ): LoginScreenState

    data class Error(
        val message: String
    ): LoginScreenState
}