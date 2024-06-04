package com.changer.basquiat.domain.model

data class RegisterForm(
    private val name: String,
    private val email: String,
    private val password: String,
)