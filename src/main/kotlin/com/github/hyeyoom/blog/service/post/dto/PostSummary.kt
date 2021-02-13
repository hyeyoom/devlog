package com.github.hyeyoom.blog.service.post.dto

import com.github.hyeyoom.blog.entity.post.Post
import java.time.LocalDateTime
import java.util.stream.Collectors

data class PostSummary(
    val title: String,
    val summary: String,
    val category: CategoryDto,
    val tags: MutableList<TagDto>,
    val createdDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime
) {
    companion object {
        fun toDto(post: Post): PostSummary {
            val tags = post.postTags.stream()
                .map { TagDto(it.tag.name) }
                .collect(Collectors.toList())
            return PostSummary(
                post.title, post.summary,
                CategoryDto(post.category.name), tags,
                post.createdDate, post.lastModifiedDate
            )
        }
    }
}