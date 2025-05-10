package com.example.first.presentation

import jakarta.servlet.http.HttpServletRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first-service")
class Controller(
    private val request: HttpServletRequest
) {

    @GetMapping("/welcome")
    fun healthCheck(): String {
        println("Server Port : ${request.serverPort}")
        return "Welcome to the First Service"
    }

    @GetMapping("/message")
    fun checkFirstRequestHeader(
    ): String {
        println("Server Port : ${request.serverPort}")
        return "OK"
    }
}