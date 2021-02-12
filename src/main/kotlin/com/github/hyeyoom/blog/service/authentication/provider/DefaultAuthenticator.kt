package com.github.hyeyoom.blog.service.authentication.provider

import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.service.authentication.UserDetailsService
import com.github.hyeyoom.blog.service.authentication.dto.Authentication
import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken
import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException

class DefaultAuthenticator(
    private val userDetailsService: UserDetailsService
) : Authenticator {
    override fun authenticate(token: AuthenticationToken): Authentication {
        val principal = token.principal
        val userDetails: UserDetails = userDetailsService.loadUserByEmail(principal)
        checkAuthentication(userDetails, token)
        return Authentication(userDetails)
    }

    private fun checkAuthentication(userDetails: UserDetails, token: AuthenticationToken) {
        if (!userDetails.checkCredentials(token.credential)) {
            throw AuthenticationException("인증 정보가 일치하지 않습니다.")
        }
    }
}