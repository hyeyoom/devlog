package com.github.hyeyoom.blog.entity.authentication

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.config.converter.PasswordFieldStringConverter
import javax.persistence.Column
import javax.persistence.Convert
import javax.persistence.Entity

@Entity
class BasicAuthentication(

    account: Account,

    @Column(nullable = false)
    @Convert(converter = PasswordFieldStringConverter::class)
    val password: String
) : Authentication(account = account)