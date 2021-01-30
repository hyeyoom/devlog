package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Tag(name: String) : BaseTimeEntity() {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    val id: Long? = null

    val name: String = name
}