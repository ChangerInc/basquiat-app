package com.changer.basquiat.presentation.ui.circle

import com.changer.basquiat.presentation.ui.historic.HistoricScreenState

interface CircleScreenState {
    data class Loading(
        var loading: Boolean
    ): CircleScreenState

    data class Success(
        val message: String
    ) : CircleScreenState

    data class Error(
        val message: String
    ) : CircleScreenState
}