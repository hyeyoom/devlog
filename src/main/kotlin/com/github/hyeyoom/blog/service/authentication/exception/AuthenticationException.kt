package com.github.hyeyoom.blog.service.authentication.exception

class AuthenticationException : RuntimeException {
    constructor(message: String): super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}