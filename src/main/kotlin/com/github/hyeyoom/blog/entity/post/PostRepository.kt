package com.github.hyeyoom.blog.entity.post

import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface PostRepository : JpaRepository<Post, Long> {

    @Query(
        value = "SELECT p FROM " +
                "Post p JOIN FETCH p.account pa JOIN FETCH p.category c ORDER BY p.lastModifiedDate DESC",
        countQuery = "SELECT count(p) FROM Post p"
    )
    fun findPosts(pageable: Pageable): Page<Post>
}