package com.github.hyeyoom.blog.config.converter

import com.github.hyeyoom.blog.repository.SecurityContextHolder
import com.github.hyeyoom.blog.service.authentication.UserDetails
import org.springframework.core.MethodParameter
import org.springframework.stereotype.Component
import org.springframework.web.bind.support.WebDataBinderFactory
import org.springframework.web.context.request.NativeWebRequest
import org.springframework.web.method.support.HandlerMethodArgumentResolver
import org.springframework.web.method.support.ModelAndViewContainer

@Component
class CurrentUserHandlerMethodArgumentResolver : HandlerMethodArgumentResolver {

    override fun supportsParameter(parameter: MethodParameter): Boolean {
        return parameter.parameterType.isAssignableFrom(UserDetails::class.java) &&
                parameter.hasParameterAnnotation(CurrentUser::class.java)
    }

    override fun resolveArgument(
        parameter: MethodParameter,
        mavContainer: ModelAndViewContainer?,
        webRequest: NativeWebRequest,
        binderFactory: WebDataBinderFactory?
    ): Any? {
        if (SecurityContextHolder.isEmptyContext()) {
            return null
        }
        return SecurityContextHolder.getContext().getAuthentication().result
    }
}