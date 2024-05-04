package com.changer.basquiat.ui.login.data

import java.util.UUID

data class UsuarioToken(
    private val id: UUID,
    private val nome: String,
    private val email: String,
    private val fotoPerfil: String,
    private val token: String,
)