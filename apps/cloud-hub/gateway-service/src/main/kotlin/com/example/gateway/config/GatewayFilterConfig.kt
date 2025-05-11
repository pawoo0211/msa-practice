package com.example.gateway.config

import org.springframework.cloud.gateway.route.RouteLocator
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class GatewayFilterConfig {
    @Bean
    fun gatewayRoute(builder: RouteLocatorBuilder): RouteLocator {
        return builder.routes().route("second-service") { r ->
            r.path("/second-service/**")
                .filters {
                    f -> f
                        .addRequestHeader("second-request", "second-request-header")
                        .addResponseHeader("second-response", "second-response-header")
                }
                .uri("http://localhost:8082")
        }.build()
    }
}