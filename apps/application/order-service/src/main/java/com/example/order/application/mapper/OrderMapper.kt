package com.example.order.application.mapper

import com.example.order.application.dto.request.CreateOrderRequest
import com.example.order.domain.CreateOrderCommand
import com.example.order.domain.OrderEntity
import org.springframework.stereotype.Component
import java.util.*

@Component
class OrderMapper {
    fun toCreateOrderCommand(
        userId: String,
        createOrderRequest: CreateOrderRequest
    ): CreateOrderCommand {
        return CreateOrderCommand(
            userId = userId,
            productId = createOrderRequest.productId,
            quantity = createOrderRequest.quantity,
            unitPrice = createOrderRequest.unitPrice,
            totalPrice = createOrderRequest.quantity * createOrderRequest.unitPrice
        )
    }

    fun toOrder(
        createOrderCommand: CreateOrderCommand
    ): OrderEntity {
        return OrderEntity(
            orderId = UUID.randomUUID().toString(),
            quantity = createOrderCommand.quantity,
            unitPrice = createOrderCommand.unitPrice,
            totalPrice = createOrderCommand.totalPrice,
            userId = createOrderCommand.userId,
            productId = createOrderCommand.productId
        )
    }
}