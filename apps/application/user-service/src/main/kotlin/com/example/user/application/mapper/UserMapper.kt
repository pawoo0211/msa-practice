package com.example.user.application.mapper

import com.example.user.application.dto.request.CreateUserRequest
import com.example.user.domain.CreateUserCommand
import com.example.user.domain.UserEntity
import org.modelmapper.ModelMapper
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.stereotype.Component

@Component
class UserMapper(
    private val passwordEncoder: BCryptPasswordEncoder,
    private val modelMapper: ModelMapper
) {
    fun toCreateUserCommand(
        createUserRequest: CreateUserRequest
    ): CreateUserCommand {
        return CreateUserCommand(
            email = createUserRequest.email,
            encryptedPassword = passwordEncoder.encode(createUserRequest.password),
            name = createUserRequest.name
        )
    }

    fun toUser(
        createUserCommand: CreateUserCommand
    ): UserEntity {
        return modelMapper.map(createUserCommand, UserEntity::class.java)
    }
}

@Configuration
class ModelMapperConfig {
    @Bean
    fun modelMapper(): ModelMapper {
        return ModelMapper()
    }
}