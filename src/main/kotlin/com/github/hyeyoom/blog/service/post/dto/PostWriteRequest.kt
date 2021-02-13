package com.github.hyeyoom.blog.service.post.dto

data class PostWriteRequest(
    val title: String,
    val summary: String,
    val content: String,
    val category: String,
    val email: String
)