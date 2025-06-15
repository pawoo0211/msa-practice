package com.example.order.application.service

import com.example.order.application.dto.response.CreateOrderResponse
import com.example.order.application.dto.response.FindOrderResponse
import com.example.order.application.mapper.OrderMapper
import com.example.order.application.message.KafkaProducer
import com.example.order.domain.CreateOrderCommand
import com.example.order.domain.OrderRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class OrderServiceImpl(
    private val orderMapper: OrderMapper,
    private val orderRepository: OrderRepository,
    private val kafkaProducer: KafkaProducer
) : OrderService {
    override fun createOrder(createOrderCommand: CreateOrderCommand): CreateOrderResponse {
        val orderEntity = orderMapper.toOrder(createOrderCommand)
        orderEntity.orderId = UUID.randomUUID().toString()
        val savedOrder = orderRepository.save(orderEntity)
        /**
         * As-Is: 주문 저장 후 kafkaProducer를 명시적으로 호출
         * To-Be: 주문 저장 후 kafkaProducer를 이벤트 기반으로 호출
         */
        kafkaProducer.send("example-catalog-topic", createOrderCommand)
        return CreateOrderResponse(savedOrder.id)
    }

    override fun findByOrderId(orderId: String): FindOrderResponse {
        val orderEntity = orderRepository.findByOrderId(orderId)
        return FindOrderResponse(
            orderEntity.orderId,
            orderEntity.productId,
            orderEntity.quantity,
            orderEntity.unitPrice,
            orderEntity.totalPrice
        )
    }

    override fun findByUserId(userId: String): List<FindOrderResponse> {
        val orderEntityList = orderRepository.findAllByUserId(userId)
        return orderEntityList.map {
            FindOrderResponse(
                it.orderId,
                it.productId,
                it.quantity,
                it.unitPrice,
                it.totalPrice
            )
        }
    }
}