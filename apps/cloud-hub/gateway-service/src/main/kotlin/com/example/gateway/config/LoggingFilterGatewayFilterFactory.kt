package com.example.gateway.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.core.Ordered
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class LoggingFilterGatewayFilterFactory :
    AbstractGatewayFilterFactory<LoggingFilterGatewayFilterFactory.Config>(Config::class.java) {
    class Config {
        var baseMessage: String = ""
        var preLogger: Boolean = true
        var postLogger: Boolean = true
    }

    override fun apply(config: Config): GatewayFilter {
        return OrderedGatewayFilter({ exchange, chain ->
            val servletRequest = exchange.request
            val servletResponse = exchange.response
            if (config.preLogger) {
                println("${config.baseMessage} request_id : ${servletRequest.id}")
            }

            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        println("${config.baseMessage} response_status : ${servletResponse.statusCode}")
                    }
                }
            )
        }, Ordered.HIGHEST_PRECEDENCE)
    }
}