package com.example.user.presentation

import com.example.user.application.dto.request.CreateUserRequest
import com.example.user.application.dto.response.CreateUserResponse
import com.example.user.application.dto.response.FindUserResponse
import com.example.user.application.mapper.UserMapper
import com.example.user.application.service.UserService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/user-service")
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

    @PostMapping("/user")
    fun createUser(
        @RequestBody createUserRequest: CreateUserRequest
    ): ResponseEntity<CreateUserResponse> {
        val createUserCommand = userMapper.toCreateUserCommand(createUserRequest)
        val createUserResponse = userService.createUser(createUserCommand)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createUserResponse)
    }

    @GetMapping("/{userId}")
    fun findUser(
        @PathVariable(name = "userId") userId: String
    ): ResponseEntity<FindUserResponse> {
        val findUserResponse = userService.findUserByEmail(userId)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(findUserResponse)
    }

    @GetMapping("/users")
    fun findUser(): ResponseEntity<List<FindUserResponse>> {
        val findUserResponseList = userService.findAllUser()

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(findUserResponseList)
    }
}