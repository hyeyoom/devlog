package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uq_tag_name", columnNames = ["name"])
    ]
)
class Tag(name: String) : BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tag_id")
    val id: Long? = null

    @Column(nullable = false, updatable = false)
    val name: String = name.toLowerCase()

    @OneToMany(mappedBy = "tag", cascade = [CascadeType.PERSIST])
    val postTags: MutableSet<PostTag> = mutableSetOf()
}