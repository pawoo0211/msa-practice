package com.example.user.domain

import java.time.LocalDateTime

data class CreateUserCommand(
    val email: String,
    val encryptedPassword: String,
    val name: String,
    val createdAt: LocalDateTime = LocalDateTime.now()
)