package com.github.hyeyoom.blog.service.authentication

import com.github.hyeyoom.blog.repository.SecurityContextHolder
import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken
import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationFailureHandler
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationSuccessHandler
import com.github.hyeyoom.blog.service.authentication.provider.Authenticator
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

abstract class AbstractAuthenticationInterceptor(
    private val authenticator: Authenticator,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler,
    private val authenticationFailureHandler: AuthenticationFailureHandler,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try {
            val authentication = attemptAuthentication(request, response)
            handleSuccess(request, response, authentication)
        } catch (e: AuthenticationException) {
            e.printStackTrace()
            handleFailure(request, response, e)
        }
        return false
    }

    private fun handleSuccess(request: HttpServletRequest, response: HttpServletResponse, authentication: Authentication) {
        SecurityContextHolder.getContext().setAuthentication(authentication)
        authenticationSuccessHandler.onAuthenticationSuccess(request, response, authentication)
    }

    private fun handleFailure(request: HttpServletRequest, response: HttpServletResponse, e: AuthenticationException) {
        SecurityContextHolder.clear()
        authenticationFailureHandler.onAuthenticationFailure(request, response, e)
    }

    private fun attemptAuthentication(request: HttpServletRequest, response: HttpServletResponse): Authentication {
        val token = convert(request)
        return authenticator.authenticate(token)
    }

    abstract fun convert(request: HttpServletRequest): AuthenticationToken
}