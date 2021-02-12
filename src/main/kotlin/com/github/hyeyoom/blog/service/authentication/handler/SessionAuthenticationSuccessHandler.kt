package com.github.hyeyoom.blog.service.authentication.handler

import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import com.github.hyeyoom.blog.service.authentication.dto.SecurityContext
import com.github.hyeyoom.blog.service.session.SessionKey
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class SessionAuthenticationSuccessHandler : AuthenticationSuccessHandler {
    override fun onAuthenticationSuccess(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authentication: Authentication
    ) {
        val session = request.getSession(true)
        session.setAttribute(SessionKey.SECURITY_CONTEXT.name, SecurityContext(authentication))
        response.status = HttpServletResponse.SC_OK
        response.sendRedirect("/")
    }
}