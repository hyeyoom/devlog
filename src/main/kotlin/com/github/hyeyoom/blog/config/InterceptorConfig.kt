package com.github.hyeyoom.blog.config

import com.github.hyeyoom.blog.service.authentication.BasicAuthenticationInterceptor
import org.springframework.context.annotation.Configuration
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@Configuration
class InterceptorConfig(
    private val basicAuthenticationInterceptor: BasicAuthenticationInterceptor
) : WebMvcConfigurer {

    override fun addInterceptors(registry: InterceptorRegistry) {
        registry.addInterceptor(basicAuthenticationInterceptor).addPathPatterns("/auth/basic")
    }
}