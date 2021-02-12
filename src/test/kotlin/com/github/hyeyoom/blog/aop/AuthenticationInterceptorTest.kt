package com.github.hyeyoom.blog.aop

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ExtendWith(SpringExtension::class)
@DisplayName("인증 인터셉터 테스트")
internal class AuthenticationInterceptorTest {

    private lateinit var interceptor: AuthenticationInterceptor
    private lateinit var req: HttpServletRequest
    private lateinit var res: HttpServletResponse

    @BeforeEach
    internal fun setUp() {
        interceptor = AuthenticationInterceptor()
        req = MockHttpServletRequest()
        res = MockHttpServletResponse()
    }

    @DisplayName("HandlerMethod가 아니라면 true를 리턴!")
    @Test
    fun testNotHandlerMethod() {
        val result = interceptor.preHandle(req, res, "abc")
        assertTrue(result)
    }
}