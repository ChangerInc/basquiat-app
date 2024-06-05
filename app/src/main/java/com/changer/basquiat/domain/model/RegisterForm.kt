package com.changer.basquiat.domain.model

data class RegisterForm(
    private val nome: String,
    private val email: String,
    private val senha: String,
)