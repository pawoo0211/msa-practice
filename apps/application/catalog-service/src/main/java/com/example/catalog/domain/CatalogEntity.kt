package com.example.catalog.domain

import jakarta.persistence.*

@Entity
@Table(name = "catalogs")
class CatalogEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(name = "product_id", nullable = false, length = 120, unique = true)
    var productId: String,

    @Column(name = "product_name", nullable = false)
    var productName: String,

    @Column(name = "stock", nullable = false)
    var stock: Int,

    @Column(name = "unit_price", nullable = false)
    var unitPrice: Int
) {
}