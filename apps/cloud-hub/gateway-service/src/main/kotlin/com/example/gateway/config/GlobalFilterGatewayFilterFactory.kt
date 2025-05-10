package com.example.gateway.config

import org.springframework.cloud.gateway.filter.GatewayFilter
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory
import org.springframework.stereotype.Component
import reactor.core.publisher.Mono

@Component
class GlobalFilterGatewayFilterFactory : AbstractGatewayFilterFactory<GlobalFilterGatewayFilterFactory.Config>(Config::class.java) {
    class Config {
        var baseMessage: String = ""
        var preLogger: Boolean = true
        var postLogger: Boolean = true
    }

    override fun apply(config: Config): GatewayFilter {
        return GatewayFilter { exchange, chain ->
            val servletRequest = exchange.request
            val servletResponse = exchange.response
            if (config.preLogger) {
                println(config.baseMessage)
                println("request_id : ${servletRequest.id}")
            }

            chain.filter(exchange).then(
                Mono.fromRunnable {
                    if (config.postLogger) {
                        println("response_status : ${servletResponse.statusCode}")
                    }
                }
            )
        }
    }
}