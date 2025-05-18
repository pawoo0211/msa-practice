package com.example.user.presentation

import com.example.user.application.dto.CreateUserRequest
import com.example.user.application.dto.CreateUserResponse
import com.example.user.application.mapper.UserMapper
import com.example.user.application.service.UserService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val environment: Environment,
    private val userMapper: UserMapper,
    private val userService: UserService
) {
    private val healthCheckMessage = "It`s Working in User Service"

    @RequestMapping("/health_check")
    fun status(): String {
        return healthCheckMessage
    }

    @RequestMapping("/welcome")
    fun welcome(): String? {
        val greetingMessage = environment.getProperty("greeting.message")
        return greetingMessage
    }

    @RequestMapping("/user")
    fun createUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): ResponseEntity<CreateUserResponse> {
        val createUserCommand = userMapper.toCreateUserCommand(createUserRequest)
        val createUserResponse = userService.createUser(createUserCommand)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createUserResponse)
    }
}