package com.github.hyeyoom.blog.entity.account

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.authentication.Authentication
import com.github.hyeyoom.blog.entity.authentication.BasicAuthentication
import javax.persistence.*

@Entity
class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    val id: Long? = null,

    val email: String,

    val username: String,

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "account", cascade = [CascadeType.ALL])
    val authentications: MutableSet<Authentication> = mutableSetOf()

) : BaseTimeEntity() {

    fun getBasicAuthentication(): BasicAuthentication {
        return authentications.filterIsInstance<BasicAuthentication>()[0]
    }

    fun addAuthentication(authentication: Authentication) {
        authentications.add(authentication)
    }
}