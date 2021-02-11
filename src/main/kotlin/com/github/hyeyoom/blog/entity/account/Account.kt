package com.github.hyeyoom.blog.entity.account

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.authentication.Authentication
import javax.persistence.*

@Entity
class Account(
    @Id
    @GeneratedValue
    @Column(name = "account_id")
    val id: Long? = null,

    val email: String,

    val username: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = [CascadeType.ALL])
    val authentications: MutableSet<Authentication> = mutableSetOf()
): BaseTimeEntity() {
    fun addAuthentication(authentication: Authentication) {
        authentications.add(authentication)
    }
}