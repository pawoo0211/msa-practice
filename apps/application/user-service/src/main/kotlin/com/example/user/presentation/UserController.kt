package com.example.user.presentation

import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController(
    private val environment: Environment
) {
    private val healthCheckMessage = "It`s Working in User Service"

    @RequestMapping("/health_check")
    fun status() : String {
        return healthCheckMessage
    }

    @RequestMapping("/welcome")
    fun welcome(): String? {
        val greetingMessage = environment.getProperty("greeting.message")
        return greetingMessage
    }
}