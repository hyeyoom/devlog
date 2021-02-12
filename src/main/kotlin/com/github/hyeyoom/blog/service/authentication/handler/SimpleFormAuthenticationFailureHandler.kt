package com.github.hyeyoom.blog.service.authentication.handler

import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SimpleFormAuthenticationFailureHandler : AuthenticationFailureHandler {
    override fun onAuthenticationFailure(
        request: HttpServletRequest,
        response: HttpServletResponse,
        failed: AuthenticationException
    ) {
        response.sendRedirect("/signin")
    }
}