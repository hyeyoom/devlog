package com.github.hyeyoom.blog.service.post.dto

import java.time.LocalDateTime

data class PostDto(
    val title: String,
    val content: String,
    val category: CategoryDto,
    val tags: MutableList<TagDto>,
    val createdDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime
)