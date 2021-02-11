package com.github.hyeyoom.blog.service.dto

data class BasicSignUpRequest(
    val email: String,
    val username: String,
    val password: String
)