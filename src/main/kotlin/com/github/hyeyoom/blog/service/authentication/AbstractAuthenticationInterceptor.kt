package com.github.hyeyoom.blog.service.authentication

import com.github.hyeyoom.blog.repository.SecurityContextHolder
import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken
import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationFailureHandler
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationSuccessHandler
import com.github.hyeyoom.blog.service.authentication.provider.Authenticator
import mu.KotlinLogging
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

private val log = KotlinLogging.logger {}

abstract class AbstractAuthenticationInterceptor(
    private val authenticator: Authenticator,
    private val authenticationSuccessHandler: AuthenticationSuccessHandler,
    private val authenticationFailureHandler: AuthenticationFailureHandler,
) : HandlerInterceptor {

    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        try {
            log.debug("login 요청됨")
            val authentication = attemptAuthentication(request, response)
            log.debug("인증 결과: $authentication")
            handleSuccess(request, response, authentication)
        } catch (e: AuthenticationException) {
            handleFailure(request, response, e)
            log.error(e.message, e)
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