package com.example.user.domain

import org.springframework.data.repository.CrudRepository

interface UserRepository: CrudRepository<UserEntity, Long> {
    fun findByEmail(email: String): UserEntity
}