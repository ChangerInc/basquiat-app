package com.changer.basquiat.domain.model

import java.util.UUID

data class UserObj (
    private val id: UUID,
    private val name: String,
    private val email: String,
    private val password: String,
)