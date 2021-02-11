package com.github.hyeyoom.blog.service

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.account.AccountRepository
import com.github.hyeyoom.blog.entity.authentication.BasicAuthentication
import com.github.hyeyoom.blog.service.dto.BasicSignUpRequest
import org.springframework.stereotype.Service

@Service
class SignUpService(
    private val repository: AccountRepository
) {

    fun signUpBasic(requestBasic: BasicSignUpRequest) {
        val account = Account(email = requestBasic.email, username = requestBasic.username)
        repository.save(account)
        val authentication = BasicAuthentication(account, requestBasic.password)
        account.addAuthentication(authentication)
    }
}