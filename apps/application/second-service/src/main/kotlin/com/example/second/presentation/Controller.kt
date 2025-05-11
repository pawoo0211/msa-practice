package com.example.second.presentation

import jakarta.servlet.http.HttpServletRequest
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

private val logger = KotlinLogging.logger {}

@RestController
@RequestMapping("/second-service")
class Controller(
    private val request: HttpServletRequest
) {
    @GetMapping("/welcome")
    fun healthCheck(): String {
        return "Welcome to the Second Service"
    }

    @GetMapping("/message")
    fun checkSecondRequestHeader(
        @RequestHeader(value = "second-request") header: String
    ): String {
        logger.info { "second-request : $header" }
        return "OK"
    }
}