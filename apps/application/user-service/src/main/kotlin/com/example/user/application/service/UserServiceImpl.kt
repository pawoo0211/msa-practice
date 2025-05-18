package com.example.user.application.service

import com.example.user.application.dto.UserRequest
import com.example.user.domain.UserEntity
import com.example.user.domain.UserRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserServiceImpl(
    private val userRepository: UserRepository
): UserService {
    override fun createUser(userRequest: UserRequest) {
        // ToDo: 행위 기반으로 클래스 변경
        // ToDo: Command/Query 기반 VO 설계 진행
        // ToDo: ModelMapper를 이용하여 디 커플링 처리
        val email = userRequest.email
        val password = userRequest.password
        val name = userRequest.name

        val user = UserEntity(
            email = email,
            encryptedPassword = password,
            name = name
        )

        userRepository.save(user)
    }
}