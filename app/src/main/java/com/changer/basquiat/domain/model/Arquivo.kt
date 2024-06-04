package com.changer.basquiat.domain.model

import java.math.BigDecimal
import java.util.UUID

data class Arquivo (
    val idArquivo: UUID,
    val nome: String,
    val criacao: String,
    val tamanho: BigDecimal,
    val extensao: String,
    val urlArquivo: String
)