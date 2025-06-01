package com.example.order.application.dto.request

data class CreateOrderRequest(
    val productId: String,
    val quantity: Int,
    val unitPrice: Int
)