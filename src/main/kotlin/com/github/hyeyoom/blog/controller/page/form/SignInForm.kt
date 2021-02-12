package com.github.hyeyoom.blog.controller.page.form

import javax.validation.constraints.Email
import javax.validation.constraints.NotEmpty

data class SignInForm(
    @field:Email(message = "잘못된 이메일입니다.")
    val email: String?,

    @field:NotEmpty(message = "비밀번호는 필수입니다.")
    val password: String?
) {
    constructor() : this(null, null)
}