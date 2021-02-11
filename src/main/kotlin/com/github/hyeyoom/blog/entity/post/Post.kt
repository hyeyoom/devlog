package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.account.Account
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class Post(
    @Id
    @GeneratedValue
    val id: Long? = null,

    @Column(nullable = false)
    val title: String,

    @Column(nullable = false)
    val content: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.PERSIST])
    val postTags: MutableSet<PostTag> = mutableSetOf()
) : BaseTimeEntity() {

    fun addTag(tag: Tag) {
        val postTag = PostTag(this, tag)
        postTags.add(postTag)
    }
}