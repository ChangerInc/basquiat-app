package com.changer.basquiat.domain.model

import java.util.UUID

data class UsuarioToken(
    private var userId: UUID,
    private var nome: String,
    private var email: String,
    private var fotoPerfil: String,
    private var token: String,
) {
    fun getNome(): String {
        return this.nome
    }

    fun getEmail(): String {
        return this.email
    }

    fun getId(): UUID {
        return this.userId
    }

    fun getFotoPerfil(): String {
        return this.fotoPerfil
    }


}