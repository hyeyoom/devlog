package com.github.hyeyoom.blog.entity.post

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [UniqueConstraint(name = "uq_category_name", columnNames = ["name"])]
)
class Category(name: String): BaseTimeEntity() {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    val id: Long? = null

    @Column(nullable = false)
    val name: String = name.toLowerCase()
}