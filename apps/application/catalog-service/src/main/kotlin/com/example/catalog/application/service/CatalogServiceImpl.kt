package com.example.catalog.application.service

import com.example.catalog.application.dto.response.FindCatalogResponse
import com.example.catalog.domain.CatalogRepository
import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Service

@Service
class CatalogServiceImpl(
    private val catalogRepository: CatalogRepository
) : CatalogService {
    override fun findAllCatalog(): List<FindCatalogResponse> {
        val catalogs = catalogRepository.findAll()
        return catalogs.map {
            FindCatalogResponse(
                productName = it.productName,
                stock = it.stock,
                unitPrice = it.unitPrice
            )
        }
    }

    @KafkaListener(topics = arrayOf("example-catalog-topic"))
    override fun updateQuantity(kafkaMessage: String) {
        var map: Map<String, Any> = mutableMapOf()
        val mapper = ObjectMapper()

        try {
            map = mapper.readValue(kafkaMessage, object : TypeReference<Map<String, Any>>() {})
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val productId = map.get("productId").toString()
        val catalogEntity = catalogRepository.findByProductId(productId)

        if (catalogEntity != null) {
            val quantity = (map["quantity"].toString()).toIntOrNull() ?: 0
            catalogEntity.stock = catalogEntity.stock - quantity
            catalogRepository.save(catalogEntity)
        }
    }
}