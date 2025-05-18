package com.example.user.util

import java.util.*

class Base64Encoder {
    companion object {
        fun encode(
            input: String
        ): String {
            return Base64
                .getEncoder()
                .encodeToString(input.toByteArray(Charsets.UTF_8))
        }
    }
}