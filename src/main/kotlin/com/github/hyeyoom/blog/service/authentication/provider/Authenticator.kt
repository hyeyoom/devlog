package com.github.hyeyoom.blog.service.authentication.provider

import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken

interface Authenticator {
    fun authenticate(token: AuthenticationToken): Authentication
}