package com.github.hyeyoom.blog.service.account.dto

data class BasicSignUpRequest(
    val email: String,
    val username: String,
    val password: String
)