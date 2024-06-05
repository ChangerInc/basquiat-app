package com.changer.basquiat.domain.model

import java.util.UUID

data class Circulo (
    val id: UUID,
    val nomeCirculo: String,
    val dono: UUID,
//    val membros: List<UsuarioFotoDto>,
    val arquivos: List<Arquivo>
)