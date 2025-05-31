package com.example.user.application.service

import com.example.user.application.dto.CreateUserResponse
import com.example.user.application.mapper.UserMapper
import com.example.user.domain.CreateUserCommand
import com.example.user.domain.UserEntity
import com.example.user.domain.UserRepository
import org.springframework.stereotype.Service

@Service
class UserServiceImpl(
    private val userMapper: UserMapper,
    private val userRepository: UserRepository
): UserService {
    override fun createUser(
        createUserCommand: CreateUserCommand
    ): CreateUserResponse {
        val user = userMapper.toUser(createUserCommand)
        val savedUser = userRepository.save(user)
        return CreateUserResponse(savedUser.id)
    }
}