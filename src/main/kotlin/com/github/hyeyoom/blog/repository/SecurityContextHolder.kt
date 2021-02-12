package com.github.hyeyoom.blog.repository

import com.github.hyeyoom.blog.service.authentication.dto.SecurityContext

object SecurityContextHolder {
    private val contextHolder: ThreadLocal<SecurityContext> = ThreadLocal()
    private val EMPTY_CONTEXT = SecurityContext()

    fun clear() {
        contextHolder.remove()
    }

    fun isEmptyContext(): Boolean {
        return contextHolder.get()?.getAuthentication()?.result == null
    }

    fun getContext(): SecurityContext {
        return contextHolder.get() ?: EMPTY_CONTEXT
    }

    fun setContext(context: SecurityContext) {
        contextHolder.set(context)
    }
}