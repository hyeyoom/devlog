package com.github.hyeyoom.blog.service.post

import com.github.hyeyoom.blog.entity.post.Category
import com.github.hyeyoom.blog.entity.post.CategoryRepository
import com.github.hyeyoom.blog.service.post.dto.CategoryDto
import org.springframework.stereotype.Service
import java.util.stream.Collectors

@Service
class CategoryService(
    private val repository: CategoryRepository
) {

    fun getCategories(): List<CategoryDto> {
        val categories = repository.findAll()
        return categories.stream()
            .map { CategoryDto(it.name) }
            .collect(Collectors.toList())
    }

    fun findCategory(name: String): Category? {
        return repository.findCategoryByName(name)
    }

    fun createNewCategory(name: String): Category {
        return repository.save(Category(name))
    }
}