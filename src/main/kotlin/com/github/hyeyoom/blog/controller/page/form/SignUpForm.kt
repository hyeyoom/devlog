package com.github.hyeyoom.blog.controller.page.form

import com.github.hyeyoom.blog.service.account.dto.BasicSignUpRequest
import java.lang.IllegalStateException
import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SignUpForm(
    @field:Email(message = "잘못된 이메일입니다.")
    val email: String?,

    @field:NotEmpty(message = "사용자 이름은 필수입니다.")
    val username: String?,

    @field:NotEmpty(message = "비밀번호는 필수입니다.")
    val password: String?
) {
    constructor() : this(null, null, null)
    fun toRequest(): BasicSignUpRequest {
        if (email == null || username == null || password == null) {
            throw IllegalStateException("이왜진?")
        }
        return BasicSignUpRequest(email, username, password)
    }
}