package com.example.order.application.service

import com.example.order.application.dto.response.CreateOrderResponse
import com.example.order.application.dto.response.FindOrderResponse
import com.example.order.domain.CreateOrderCommand

interface OrderService {
    fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse
    fun findByOrderId(orderId: String): FindOrderResponse
    fun findByUserId(userId: String): List<FindOrderResponse>
}