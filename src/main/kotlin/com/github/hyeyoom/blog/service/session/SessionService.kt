package com.github.hyeyoom.blog.service.session

import org.springframework.stereotype.Service
import javax.servlet.http.HttpSession

@Service
class SessionService(
    private val session: HttpSession
) {
    fun set(key: SessionKey, obj: Any) {
        session.setAttribute(key.name, obj)
    }

    @Suppress("UNCHECKED_CAST")
    fun <T> get(key: SessionKey): T? {
        return session.getAttribute(key.name) as T
    }
}