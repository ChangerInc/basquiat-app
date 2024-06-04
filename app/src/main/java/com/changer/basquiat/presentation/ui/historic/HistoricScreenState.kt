package com.changer.basquiat.presentation.ui.historic

interface HistoricScreenState {
    data class Loading(
        val loading: Boolean
    ) : HistoricScreenState

    data class Success(
        val message: String
    ) : HistoricScreenState

    data class Error(
        val message: String
    ) : HistoricScreenState
}