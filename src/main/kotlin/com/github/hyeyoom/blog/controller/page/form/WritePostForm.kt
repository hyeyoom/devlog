package com.github.hyeyoom.blog.controller.page.form

import javax.validation.constraints.NotEmpty

data class WritePostForm(
    @field:NotEmpty(message = "제목은 필수입니다.")
    val title: String?,

    @field:NotEmpty(message = "내용은 필수입니다.")
    val content: String?,

    @field:NotEmpty(message = "카테고리는 필수입니다.")
    val category: String?
) {
    constructor() : this(null, null, null)
}