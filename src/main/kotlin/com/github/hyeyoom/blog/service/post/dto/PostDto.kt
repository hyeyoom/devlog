package com.github.hyeyoom.blog.service.post.dto

import com.github.hyeyoom.blog.entity.post.Post
import java.time.LocalDateTime
import java.util.stream.Collectors

data class PostDto(
    val title: String,
    val content: String,
    val category: CategoryDto,
    val tags: MutableList<TagDto>,
    val createdDate: LocalDateTime,
    val lastModifiedDate: LocalDateTime
) {
    companion object {
        fun toDto(post: Post): PostDto {
            val tags = post.postTags.stream()
                .map { TagDto(it.tag.name) }
                .collect(Collectors.toList())
            return PostDto(
                post.title, post.content,
                CategoryDto(post.category.name), tags,
                post.createdDate, post.lastModifiedDate
            )
        }
    }
}