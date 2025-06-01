package com.example.user.application.service

import com.example.user.application.dto.response.CreateUserResponse
import com.example.user.application.dto.response.FindUserResponse
import com.example.user.domain.CreateUserCommand
import com.example.user.domain.FindUserCommand

interface UserService {
    fun createUser(createUserCommand: CreateUserCommand): CreateUserResponse
    fun findUserByEmail(email: String): FindUserResponse
    fun findAllUser(): List<FindUserResponse>
}