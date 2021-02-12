package com.github.hyeyoom.blog.service.authentication.dto

class SecurityContext(
    private var authentication: Authentication
) {
    constructor() : this(Authentication(Unit))

    fun setAuthentication(authentication: Authentication) {
        this.authentication = authentication
    }

    fun getAuthentication(): Authentication {
        return authentication
    }
}