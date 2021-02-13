package com.github.hyeyoom.blog.entity.account

import com.github.hyeyoom.blog.entity.BaseTimeEntity
import com.github.hyeyoom.blog.entity.authentication.Authentication
import com.github.hyeyoom.blog.entity.authentication.BasicAuthentication
import javax.persistence.*

@Entity
@Table(
    uniqueConstraints = [
        UniqueConstraint(name = "uq_account_email", columnNames = ["email"]),
        UniqueConstraint(name = "uq_account_username", columnNames = ["username"])
    ]
)
class Account(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    val id: Long? = null,

    @Column(nullable = false)
    val email: String,

    @Column(nullable = false)
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