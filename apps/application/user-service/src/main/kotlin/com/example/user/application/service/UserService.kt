package com.example.user.application.service

import com.example.user.application.dto.CreateUserResponse
import com.example.user.domain.CreateUserCommand

interface UserService {
    fun createUser(createUserCommand: CreateUserCommand): CreateUserResponse
}