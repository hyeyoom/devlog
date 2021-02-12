package com.github.hyeyoom.blog.service.account

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.account.AccountRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class AccountQueryService(
    private val repository: AccountRepository
) {
    fun findByEmail(email: String): Account? {
        return repository.findByEmail(email)
    }
}