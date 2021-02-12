package com.github.hyeyoom.blog.service.authentication.provider

import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.service.authentication.UserDetailsService
import com.github.hyeyoom.blog.service.authentication.dto.AuthenticationToken
import com.github.hyeyoom.blog.service.authentication.exception.AuthenticationException
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.ArgumentMatchers.anyString
import org.mockito.Mock
import org.mockito.Mockito.`when`
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@DisplayName("기본 authenticator 테스트")
internal class DefaultAuthenticatorTest {

    @Mock
    private lateinit var userDetails: UserDetails

    @Mock
    private lateinit var userDetailsService: UserDetailsService

    private lateinit var authenticator: Authenticator

    @BeforeEach
    internal fun setUp() {
        authenticator = DefaultAuthenticator(userDetailsService)
    }

    @DisplayName("인증이 제대로 된 경우를 테스트 한다.")
    @Test
    fun testAuthentication() {

        // given
        val token = setGiven(true)

        // when
        val result = authenticator.authenticate(token)

        // then
        assertNotNull(result)
    }

    @DisplayName("인증이 실패하였다.")
    @Test
    fun testAuthenticationFailure() {

        // given
        val token = setGiven(false)

        // when & then
        assertThrows<AuthenticationException>("인증 예외가 발생한다.") {
            authenticator.authenticate(token)
        }
    }

    private fun setGiven(checkCredentialsWillReturn: Boolean): AuthenticationToken {
        val token = AuthenticationToken("a@b.com", "1")
        `when`(userDetailsService.loadUserByEmail(anyString())).thenReturn(userDetails)
        `when`(userDetails.checkCredentials(anyString())).thenReturn(checkCredentialsWillReturn)
        return token
    }
}