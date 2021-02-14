package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.account.Account
import java.util.stream.Collectors
import javax.persistence.*
import javax.persistence.FetchType.LAZY

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uq_post_title", columnNames = ["title"]),
    ]
)
class Post(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @Column(nullable = false)
    var title: String,

    @Column(nullable = false)
    var summary: String,

    @Lob
    @Column(nullable = false)
    var content: String,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "account_id", nullable = false)
    val account: Account,

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "category_id", nullable = false)
    var category: Category,

    @OneToMany(mappedBy = "post", cascade = [CascadeType.PERSIST])
    val postTags: MutableSet<PostTag> = mutableSetOf()
) : BaseTimeEntity() {

    fun addTag(tag: Tag) {
        val postTag = PostTag(this, tag)
        postTags.add(postTag)
    }

    fun tags(): MutableSet<Tag> {
        return postTags.stream()
            .map { it.tag }
            .collect(Collectors.toSet())
    }

    override fun toString(): String {
        return "Post(id=$id, title='$title', content='$content', account=$account, category=$category, postTags=$postTags)"
    }
}