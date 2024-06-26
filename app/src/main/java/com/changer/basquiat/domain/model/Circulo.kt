package com.changer.basquiat.domain.model

import java.util.UUID

data class Circulo (
    val id: UUID,
    val nomeCirculo: String,
    val dono: UsuarioToken,
    val membros: List<UserMember>,
    val arquivos: List<Arquivo>
)