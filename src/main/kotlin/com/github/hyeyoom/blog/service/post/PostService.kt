package com.github.hyeyoom.blog.service.post

import com.github.hyeyoom.blog.entity.post.Category
import com.github.hyeyoom.blog.entity.post.PostRepository
import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import org.springframework.stereotype.Service

@Service
class PostService(
    private val repository: PostRepository,
    private val categoryService: CategoryService
) {

    fun write(request: PostWriteRequest) {
        val category = findCategory(request.category)
    }

    private fun findCategory(name: String): Category {
        return categoryService.findCategory(name) ?: categoryService.createNewCategory(name)
    }
}