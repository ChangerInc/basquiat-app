package com.changer.basquiat.ui.login.data

data class UserForm(
    private val email: String,
    private val senha: String,
) {

    fun getEmail(): String {
        return email
    }
    fun getSenha(): String {
        return senha
    }
}