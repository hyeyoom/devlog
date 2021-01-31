package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.account.Account
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
class Post(
    title: String, content: String,
    account: Account, category: Category
) : BaseTimeEntity() {

    @Id
    @GeneratedValue
    val id: Long? = null

    @Column(nullable = false)
    val title: String = title

    @Column(nullable = false)
    val content: String = content

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account = account

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    val category: Category = category

    @OneToMany(mappedBy = "post", cascade = [CascadeType.PERSIST])
    val postTags: MutableSet<PostTag> = mutableSetOf()

    fun addTag(tag: Tag) {
        val postTag = PostTag(this, tag)
        postTags.add(postTag)
    }
}