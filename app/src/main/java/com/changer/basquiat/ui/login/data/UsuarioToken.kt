package com.changer.basquiat.ui.login.data

import java.lang.reflect.Constructor
import java.util.UUID

data class UsuarioToken(
    private var id: UUID,
    private var nome: String,
    private var email: String,
    private var fotoPerfil: String,
    private var token: String,
) {
    fun getNome(): String {
        return this.nome
    }
}