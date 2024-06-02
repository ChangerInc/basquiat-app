package com.changer.basquiat.domain.usecase

class ValidatePasswordUseCase {
    fun execute(password: String, confirmPassword: String): Boolean {
        return password == confirmPassword && password.length >= 8
    }
}