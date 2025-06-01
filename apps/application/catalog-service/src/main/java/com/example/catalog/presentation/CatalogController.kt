package com.example.catalog.presentation

import com.example.catalog.application.dto.response.FindCatalogResponse
import com.example.catalog.application.service.CatalogService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/catalog-service")
class CatalogController(
    private val environment: Environment,
    private val catalogService: CatalogService
) {
    @RequestMapping("/health_check")
    fun status(): String {
        return "It`s Working in User Service"
    }

    @RequestMapping("/welcome")
    fun welcome(): String? {
        val greetingMessage = environment.getProperty("greeting.message")
        return greetingMessage
    }

    @GetMapping("/catalogs")
    fun findAllUser(): ResponseEntity<List<FindCatalogResponse>> {
        val findCatalogResponseList = catalogService.findAllCatalog()

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(findCatalogResponseList)
    }
}