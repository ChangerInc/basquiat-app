package com.changer.basquiat.domain.model

import java.util.UUID

data class Usuario(
    private val uuid: UUID,
    private val nome: String,
    private val senha: String,
)