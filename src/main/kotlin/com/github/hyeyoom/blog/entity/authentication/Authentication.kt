package com.github.hyeyoom.blog.entity.authentication

import com.github.hyeyoom.blog.entity.account.Account
import javax.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
abstract class Authentication(

    @Id
    @GeneratedValue
    @Column(name = "authentication_id")
    val id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id")
    val account: Account
)