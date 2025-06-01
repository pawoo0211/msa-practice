package com.example.order.application.dto.response

data class FindOrderResponse(
    val orderId: String,
    val productId: String,
    val quantity: Int,
    val unitPrice: Int,
    val totalPrice: Int,
)
