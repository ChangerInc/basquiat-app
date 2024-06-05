package com.changer.basquiat.presentation.ui.conversion

interface ConversionScreenState {
    data class Loading(
        val loading: Boolean,
        val message: String
    ) : ConversionScreenState

    data class Success(
        val message: String
    ) : ConversionScreenState

    data class Error(
        val message: String
    ) : ConversionScreenState
}