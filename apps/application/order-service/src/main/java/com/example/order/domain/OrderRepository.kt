package com.example.order.domain

import org.springframework.data.repository.CrudRepository

interface OrderRepository: CrudRepository<OrderEntity, Long> {
    fun findByOrderId(orderId: String): OrderEntity
    fun findAllByUserId(userId: String): List<OrderEntity>
}