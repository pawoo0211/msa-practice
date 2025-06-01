package com.example.order.domain

import jakarta.persistence.*

@Entity
@Table(name = "orders")
class OrderEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(name = "order_id", unique = true)
    var orderId: String,

    @Column(name = "quantity", nullable = false)
    var quantity: Int,

    @Column(name = "unit_price", nullable = false)
    var unitPrice: Int,

    @Column(name = "total_price", nullable = false)
    var totalPrice: Int,

    @Column(name = "user_id", unique = false)
    var userId: String,

    @Column(name = "product_id", nullable = false, length = 120, unique = true)
    var productId: String
    ) {
}