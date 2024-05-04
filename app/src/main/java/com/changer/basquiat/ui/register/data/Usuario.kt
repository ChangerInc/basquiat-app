package com.changer.basquiat.ui.home.data

import java.util.UUID

data class Usuario(
    private val uuid: UUID,
    private val nome: String,
    private val senha: String,
)