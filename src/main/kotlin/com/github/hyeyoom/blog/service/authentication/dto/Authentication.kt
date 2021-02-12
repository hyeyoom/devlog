package com.github.hyeyoom.blog.service.authentication.dto

import com.github.hyeyoom.blog.service.authentication.UserDetails

data class Authentication(
    val result: UserDetails?
)