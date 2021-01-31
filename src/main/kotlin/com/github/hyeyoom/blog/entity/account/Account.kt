package com.github.hyeyoom.blog.entity.account

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account(email: String, username: String): BaseTimeEntity() {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    val id: Long? = null

    val email: String = email

    val username: String = username
}