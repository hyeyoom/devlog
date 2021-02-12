package com.github.hyeyoom.blog.config

import com.github.hyeyoom.blog.service.authentication.BasicAuthenticationInterceptor
import com.github.hyeyoom.blog.service.authentication.UserDetailsService
import com.github.hyeyoom.blog.service.authentication.handler.SessionAuthenticationSuccessHandler
import com.github.hyeyoom.blog.service.authentication.handler.SimpleFormAuthenticationFailureHandler
import com.github.hyeyoom.blog.service.authentication.provider.DefaultAuthenticator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Beans(
    private val userDetailsService: UserDetailsService
) {

    @Bean
    fun defaultAuthenticator(): DefaultAuthenticator {
        return DefaultAuthenticator(userDetailsService)
    }

    @Bean
    fun sessionAuthenticationSuccessHandler(): SessionAuthenticationSuccessHandler {
        return SessionAuthenticationSuccessHandler()
    }

    @Bean
    fun simpleFormAuthenticationFailureHandler(): SimpleFormAuthenticationFailureHandler {
        return SimpleFormAuthenticationFailureHandler()
    }

    @Bean
    fun basicAuthenticationInterceptor(
        autheneticator: DefaultAuthenticator,
        successHandler: SessionAuthenticationSuccessHandler,
        failureHandler: SimpleFormAuthenticationFailureHandler
    ): BasicAuthenticationInterceptor {
        return BasicAuthenticationInterceptor(autheneticator, successHandler, failureHandler)
    }
}