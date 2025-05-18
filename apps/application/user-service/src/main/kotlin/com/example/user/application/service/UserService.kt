package com.example.user.application.service

import com.example.user.application.dto.UserRequest

interface UserService {
    fun createUser(userRequest: UserRequest)
}