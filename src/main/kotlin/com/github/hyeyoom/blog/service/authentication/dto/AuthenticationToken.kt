package com.github.hyeyoom.blog.service.authentication.dto

data class AuthenticationToken(
    val principal: String,
    val credential: String
)