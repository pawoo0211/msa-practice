package com.example.user.domain

import jakarta.persistence.*

@Entity
@Table(name = "users")
class UserEntity(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    @Column(nullable = false, length = 50, unique = true)
    var email: String,

    @Column(nullable = false, unique = true)
    var encryptedPassword: String,

    @Column(nullable = false, length = 50)
    var name: String
) {
}