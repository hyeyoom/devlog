package com.github.hyeyoom.blog.aop

import io.mockk.every
import io.mockk.mockk
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentCaptor
import org.mockito.Mockito.*
import org.springframework.mock.web.MockHttpServletRequest
import org.springframework.mock.web.MockHttpServletResponse
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.web.method.HandlerMethod
import java.lang.reflect.Method
import javax.servlet.http.HttpServletResponse

@ExtendWith(SpringExtension::class)
@DisplayName("인증 인터셉터 테스트")
internal class AuthenticationInterceptorTest {

    private val sut = AuthenticationInterceptor()

    @DisplayName("HandlerMethod가 아니라면 true를 리턴!")
    @Test
    fun testNotHandlerMethod() {
        val req = MockHttpServletRequest();
        val res = MockHttpServletResponse()
        val result = sut.preHandle(req, res, "abc")
        assertThat(result).isTrue
    }

    @DisplayName("핸들러 메서드에 LoginRequired가 annotated고 컨텍스트가 empty인 경우 redirect가 발생한다.")
    @Test
    fun test() {
        val req = MockHttpServletRequest()
        val res = mock(HttpServletResponse::class.java)
        val method = mockk<Method>()
        val handlerMethod = mockk<HandlerMethod>()
        val uriCaptor = ArgumentCaptor.forClass(String::class.java)

        every { handlerMethod.method } returns method
        every { method.isAnnotationPresent(any<Class<LoginRequired>>()) } returns true

        val result = sut.preHandle(req, res, handlerMethod)
        verify(res, times(1)).sendRedirect(uriCaptor.capture())
        assertThat(uriCaptor.value).isEqualTo("/signin")
        assertThat(result).isFalse
    }
}
