package com.example.catalog.application.service

import com.example.catalog.application.dto.response.FindCatalogResponse
import com.example.catalog.domain.CatalogRepository
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
}