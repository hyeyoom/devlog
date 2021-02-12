package com.github.hyeyoom.blog.config.web

import com.github.hyeyoom.blog.aop.AuthenticationInterceptor
import com.github.hyeyoom.blog.service.authentication.BasicAuthenticationInterceptor
import com.github.hyeyoom.blog.service.session.SessionInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    private val basicAuthenticationInterceptor: BasicAuthenticationInterceptor,
    private val sessionInterceptor: SessionInterceptor,
    private val authenticationInterceptor: AuthenticationInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(sessionInterceptor)
        registry.addInterceptor(authenticationInterceptor)
        registry.addInterceptor(basicAuthenticationInterceptor).addPathPatterns("/auth/basic")
    }
}