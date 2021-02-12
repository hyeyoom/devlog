package com.github.hyeyoom.blog.service.authentication

import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationFailureHandler
import com.github.hyeyoom.blog.service.authentication.handler.AuthenticationSuccessHandler
import com.github.hyeyoom.blog.service.authentication.provider.Authenticator
import javax.servlet.http.HttpServletRequest

class BasicAuthenticationInterceptor(
    authenticator: Authenticator,
    authenticationSuccessHandler: AuthenticationSuccessHandler,
    authenticationFailureHandler: AuthenticationFailureHandler,
    private val EMAIL_FIELD: String = "email",
    private val PASSWORD_FIELD: String = "password"
) : AbstractAuthenticationInterceptor(authenticator, authenticationSuccessHandler, authenticationFailureHandler) {

    override fun convert(request: HttpServletRequest): AuthenticationToken {
        val email = request.getParameter(EMAIL_FIELD)
        val password = request.getParameter(PASSWORD_FIELD)
        return AuthenticationToken(email, password)
    }
}