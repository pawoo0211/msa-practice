package com.example.user.application.dto.request

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String
)