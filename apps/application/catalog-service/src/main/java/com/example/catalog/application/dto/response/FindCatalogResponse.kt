package com.example.catalog.application.dto.response

data class FindCatalogResponse(
    val productName: String,
    val stock: Int,
    val unitPrice: Int
)
