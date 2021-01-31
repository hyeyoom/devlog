package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class PostTag(post: Post, tag: Tag): BaseTimeEntity() {

    @Id
    @GeneratedValue
    @Column(name = "post_tag_id")
    val id: Long? = null

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    var post: Post = post

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "tag_id", nullable = false)
    val tag: Tag = tag
}