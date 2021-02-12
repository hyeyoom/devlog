package com.github.hyeyoom.blog.controller.page.form

import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import javax.validation.constraints.NotEmpty

data class WritePostForm(
    @field:NotEmpty(message = "제목은 필수입니다.")
    val title: String?,

    @field:NotEmpty(message = "내용은 필수입니다.")
    val content: String?,

    @field:NotEmpty(message = "카테고리는 필수입니다.")
    val category: String?
) {
    fun toRequest(email: String?): PostWriteRequest {
        if (title == null || content == null || category == null || email == null) {
            throw IllegalStateException("이왜진?")
        }
        return PostWriteRequest(title, content, category, email)
    }

    constructor() : this(null, null, null)
}