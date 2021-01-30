package com.github.hyeyoom.blog.entity.account

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.Id

@Entity
class Account {

    @Id
    @GeneratedValue
    @Column(name = "account_id")
    val id: Long? = null

    val email: String? = null

    val username: String? = null
}