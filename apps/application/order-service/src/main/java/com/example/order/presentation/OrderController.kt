package com.example.order.presentation

import com.example.order.application.dto.request.CreateOrderRequest
import com.example.order.application.dto.response.CreateOrderResponse
import com.example.order.application.dto.response.FindOrderResponse
import com.example.order.application.mapper.OrderMapper
import com.example.order.application.service.OrderService
import org.springframework.core.env.Environment
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/order-service")
class OrderController(
    private val environment: Environment,
    private val orderMapper: OrderMapper,
    private val orderService: OrderService
) {
    @RequestMapping("/health_check")
    fun status(): String {
        return "It`s Working in Order Service"
    }

    @RequestMapping("/welcome")
    fun welcome(): String? {
        val greetingMessage = environment.getProperty("greeting.message")
        return greetingMessage
    }

    @PostMapping("/{userId}/order")
    fun createOrder(
        @PathVariable(name = "userId") userId: String,
        @RequestBody createOrderRequest: CreateOrderRequest
    ): ResponseEntity<CreateOrderResponse> {
        val orderCreateCommand = orderMapper.toCreateOrderCommand(userId, createOrderRequest)
        val createOrderResponse = orderService.createOrder(orderCreateCommand)

        return ResponseEntity
            .status(HttpStatus.CREATED)
            .body(createOrderResponse)
    }

    @GetMapping("/{userId}/orders")
    fun findAllOrderByUser(
        @PathVariable(name = "userId") userId: String
    ): ResponseEntity<List<FindOrderResponse>> {
        val findOrderResponseList = orderService.findByUserId(userId)

        return ResponseEntity
            .status(HttpStatus.OK)
            .body(findOrderResponseList)
    }
}