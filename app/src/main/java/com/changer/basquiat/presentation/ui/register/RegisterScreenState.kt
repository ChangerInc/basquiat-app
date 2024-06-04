package com.changer.basquiat.presentation.ui.register

import com.changer.basquiat.domain.model.UserObj

interface RegisterScreenState {
    data object Normalize: RegisterScreenState
    data object Loading: RegisterScreenState

    data class Success(
        val data: UserObj
    ): RegisterScreenState

    data class Error(
        val message: String
    ): RegisterScreenState
}