package com.example.user.application.service

import com.example.user.application.dto.response.CreateUserResponse
import com.example.user.application.dto.response.FindUserResponse
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

    override fun findUserByEmail(email: String): FindUserResponse {
        val user = userRepository.findByEmail(email)
        return FindUserResponse(user.email, user.name)
    }

    override fun findAllUser(): List<FindUserResponse> {
        val users = userRepository.findAll()
        return users.map { FindUserResponse(it.email, it.name) }
    }
}