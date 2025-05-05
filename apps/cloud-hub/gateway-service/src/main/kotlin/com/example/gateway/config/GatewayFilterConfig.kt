package com.example.gateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayFilterConfig {
    @Bean
    fun gatewayRoute(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route("first-service") { r ->
            r.path("/first-service/**")
                .filters {
                    f -> f
                        .addRequestHeader("first-request", "first-request-header")
                        .addResponseHeader("first-response", "first-response-header")
                }
                .uri("http://localhost:8081")
        }.build()
    }
}