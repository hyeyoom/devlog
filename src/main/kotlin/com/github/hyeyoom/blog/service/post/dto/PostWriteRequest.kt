package com.github.hyeyoom.blog.service.post.dto

data class PostWriteRequest(
    val title: String,
    val content: String,
    val category: String
)