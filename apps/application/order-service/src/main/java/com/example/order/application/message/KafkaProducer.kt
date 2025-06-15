package com.example.order.application.message

import com.example.order.domain.CreateOrderCommand
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

@Component
class KafkaProducer(
    val kafkaTemplate: KafkaTemplate<String, Any>
) {
    fun send(topic: String, createOrderCommand: CreateOrderCommand) {
        val objectMapper = ObjectMapper()
        try {
            val createOrderCommandToString = objectMapper.writeValueAsString(createOrderCommand)
            kafkaTemplate.send(topic, createOrderCommandToString)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}