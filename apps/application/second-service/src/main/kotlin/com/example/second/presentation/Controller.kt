package com.example.second.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/second-service")
class Controller {
    @GetMapping("/welcome")
    fun healthCheck(): String {
        return "Welcome to the Second Service"
    }

    @GetMapping("/message")
    fun checkSecondRequestHeader(
        @RequestHeader(value = "second-request") header: String
    ): String {
        println("second-request : ${header}")
        return "OK"
    }
}