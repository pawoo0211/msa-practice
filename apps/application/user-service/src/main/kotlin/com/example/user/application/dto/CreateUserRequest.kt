package com.example.user.application.dto

data class CreateUserRequest(
    val email: String,
    val password: String,
    val name: String
)