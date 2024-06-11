package com.changer.basquiat.domain.model

import java.util.UUID

data class UserMember (
    val id: UUID,
    val nome: String,
    val fotoPerfil: String,
)