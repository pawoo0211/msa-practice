package com.example.catalog.application.service

import com.example.catalog.application.dto.response.FindCatalogResponse

interface CatalogService {
    fun findAllCatalog(): List<FindCatalogResponse>
    fun updateQuantity(kafkaMessage: String)
}