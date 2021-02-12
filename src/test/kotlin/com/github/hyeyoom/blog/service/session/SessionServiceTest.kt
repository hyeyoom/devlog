package com.github.hyeyoom.blog.service.session

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.any
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.servlet.http.HttpSession

@ExtendWith(SpringExtension::class)
@DisplayName("세션 서비스 테스트")
internal class SessionServiceTest {

    @Mock
    private lateinit var session: HttpSession

    private lateinit var service: SessionService

    @BeforeEach
    internal fun setUp() {
        service = SessionService(session)
    }

    @DisplayName("세션 set")
    @Test
    fun testSet() {
        val objectToBeStored = "abc"
        service.set(SessionKey.SECURITY_CONTEXT, objectToBeStored)
        verify(session, times(1)).setAttribute(anyString(), any())
    }

    @DisplayName("세션 get")
    @Test
    fun testGet() {
        service.get<String>(SessionKey.SECURITY_CONTEXT)
        verify(session, times(1)).getAttribute(anyString())
    }
}