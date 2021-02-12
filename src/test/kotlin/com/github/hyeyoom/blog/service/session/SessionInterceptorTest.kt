package com.github.hyeyoom.blog.service.session

import com.github.hyeyoom.blog.service.authentication.dto.SecurityContext
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mock
import org.mockito.Mockito.times
import org.mockito.Mockito.verify
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DisplayName("세션 인터셉터 테스트")
internal class SessionInterceptorTest {

    @Mock
    private lateinit var service: SessionService

    private lateinit var interceptor: SessionInterceptor

    @BeforeEach
    internal fun setUp() {
        interceptor = SessionInterceptor(service)
    }

    @DisplayName("인터셉터는 무조건 true만 리턴한다.")
    @Test
    fun test1() {
        val result = invokeMock()
        assertTrue(result)
    }

    @DisplayName("service.get은 1회만 발생한다.")
    @Test
    fun test2() {
        val result = invokeMock()
        verify(service, times(1)).get<SecurityContext>(SessionKey.SECURITY_CONTEXT)
    }

    private fun invokeMock(): Boolean {
        val req = MockHttpServletRequest()
        val res = MockHttpServletResponse()
        return interceptor.preHandle(req, res, "abc")
    }
}