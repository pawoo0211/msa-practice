package com.example.first.presentation

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/first-service")
class Controller {
    @GetMapping("/welcome")
    fun healthCheck(): String {
        return "Welcome to the First Service"
    }
}