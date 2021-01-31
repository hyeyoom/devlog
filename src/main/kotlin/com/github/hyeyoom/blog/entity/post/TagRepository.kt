package com.github.hyeyoom.blog.entity.post

import org.springframework.data.jpa.repository.JpaRepository

interface TagRepository : JpaRepository<Tag, Long>