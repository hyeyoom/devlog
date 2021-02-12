package com.github.hyeyoom.blog.service.authentication.handler

import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface AuthenticationSuccessHandler {
    fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    )
}