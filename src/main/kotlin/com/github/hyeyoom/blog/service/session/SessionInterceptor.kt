package com.github.hyeyoom.blog.service.session

import com.github.hyeyoom.blog.repository.SecurityContextHolder
import com.github.hyeyoom.blog.service.authentication.dto.SecurityContext
import org.springframework.stereotype.Component
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class SessionInterceptor(
    private val service: SessionService
): HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        val ctx: SecurityContext? = service.get(SessionKey.SECURITY_CONTEXT)
        if (ctx != null) {
            SecurityContextHolder.setContext(ctx)
        }
        return true
    }
}