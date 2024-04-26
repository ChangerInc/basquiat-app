package com.changer.basquiat.mvvm.model

import java.time.LocalDateTime
import java.util.UUID

data class Usuario(
    val id: UUID,
    private val nome: String,
    private val email: String,
    private val senha: String,
    private val plano: Boolean,
    private val dataCriacaoConta: LocalDateTime,
    private val fotoPerfil: Byte
)