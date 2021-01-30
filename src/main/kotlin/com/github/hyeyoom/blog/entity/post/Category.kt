package com.github.hyeyoom.blog.entity.post

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Category(name: String) {

    @Id
    @GeneratedValue
    @Column(name = "category_id")
    val id: Long? = null

    @Column(nullable = false)
    val name: String = name
}