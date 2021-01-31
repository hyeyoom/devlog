package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.*

@Entity
class Tag(name: String) : BaseTimeEntity() {

    @Id
    @GeneratedValue
    @Column(name = "tag_id")
    val id: Long? = null

    @Column(nullable = false, updatable = false)
    val name: String = name.toLowerCase()

    @OneToMany(mappedBy = "tag", cascade = [CascadeType.PERSIST])
    val postTags: MutableSet<PostTag> = mutableSetOf()
}