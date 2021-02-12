package com.github.hyeyoom.blog.service.authentication.handler

import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

interface AuthenticationFailureHandler {
    fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    )
}