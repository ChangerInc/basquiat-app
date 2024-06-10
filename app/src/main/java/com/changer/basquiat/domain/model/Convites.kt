package com.changer.basquiat.domain.model

import java.time.LocalDateTime
import java.util.UUID

data class Convites(
    val fotoAnfitriao: String,
    val anfitriao: String,
    val nomeCirculo: String,
    val idCirculo: UUID,
    val horario: String
)
