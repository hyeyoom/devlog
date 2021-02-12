package com.github.hyeyoom.blog.aop

import com.github.hyeyoom.blog.repository.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.method.HandlerMethod
import org.springframework.web.servlet.HandlerInterceptor
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@Component
class AuthenticationInterceptor : HandlerInterceptor {
    override fun preHandle(request: HttpServletRequest, response: HttpServletResponse, handler: Any): Boolean {
        if (handler !is HandlerMethod) {
            return true
        }
        val method = handler.method
        if (method.isAnnotationPresent(LoginRequired::class.java) && SecurityContextHolder.isEmptyContext()) {
            response.sendRedirect("/signin")
            return false
        }
        return true
    }
}