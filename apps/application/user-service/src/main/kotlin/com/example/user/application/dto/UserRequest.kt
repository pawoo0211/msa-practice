package com.example.user.application.dto

data class UserRequest(
    val email: String,
    val password: String,
    val name: String
)