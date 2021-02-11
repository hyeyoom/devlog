package com.github.hyeyoom.blog.entity.post

import org.springframework.data.jpa.repository.JpaRepository

interface CategoryRepository: JpaRepository<Category, Long> {
    fun findCategoryByName(name: String): Category?
}