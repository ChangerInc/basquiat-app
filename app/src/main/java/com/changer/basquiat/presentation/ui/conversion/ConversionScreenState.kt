package com.changer.basquiat.presentation.ui.conversion

import kotlinx.coroutines.flow.Flow

interface ConversionScreenState {
    data class Loading(
        val loading: Boolean
    ) : ConversionScreenState

    data class Success(
        val message: String
    ) : ConversionScreenState

    data class Error(
        val message: String
    ) : ConversionScreenState
}