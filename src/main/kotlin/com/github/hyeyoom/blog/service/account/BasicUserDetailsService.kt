package com.github.hyeyoom.blog.service.account

import com.github.hyeyoom.blog.entity.account.AccountRepository
import com.github.hyeyoom.blog.service.account.dto.BasicUserDetails
import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.service.authentication.UserDetailsService
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class BasicUserDetailsService(
    private val repository: AccountRepository
) : UserDetailsService {
    override fun loadUserByEmail(principal: String): UserDetails {
        val foundAccount = repository.findByEmail(principal)
        // TODO: 익셉션 정의 필요
            ?: throw IllegalArgumentException("No such user in database: $principal")
        val auth = foundAccount.getBasicAuthentication()
        return BasicUserDetails(principal, auth.password)
    }
}