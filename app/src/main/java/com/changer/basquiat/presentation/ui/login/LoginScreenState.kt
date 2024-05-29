package com.changer.basquiat.presentation.ui.login

import com.changer.basquiat.domain.model.UsuarioToken

sealed interface LoginScreenState {
    data object Normalize: LoginScreenState
    data object Loading: LoginScreenState

    data class Success(
        val data: UsuarioToken
    ): LoginScreenState

    data class Error(
        val message: String
    ): LoginScreenState
}