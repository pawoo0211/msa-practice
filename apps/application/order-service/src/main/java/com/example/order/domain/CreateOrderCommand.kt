package com.example.order.domain

data class CreateOrderCommand(
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
    val userId: String,
    val productId: String
)
