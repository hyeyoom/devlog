package com.github.hyeyoom.blog.repository

import com.github.hyeyoom.blog.service.authentication.dto.SecurityContext

object SecurityContextHolder {
    private val contextHolder: ThreadLocal<SecurityContext> = ThreadLocal()

    fun clear() {
        contextHolder.remove()
    }

    fun getContext(): SecurityContext {
        return contextHolder.get() ?: SecurityContext()
    }

    fun setContext(context: SecurityContext) {
        contextHolder.set(context)
    }
}