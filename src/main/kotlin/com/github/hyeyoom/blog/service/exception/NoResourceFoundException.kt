package com.github.hyeyoom.blog.service.exception

class NoResourceFoundException : RuntimeException {
    constructor(message: String) : super(message)
    constructor(message: String, cause: Throwable) : super(message, cause)
}