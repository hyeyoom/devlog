package com.github.hyeyoom.blog.service.authentication

interface UserDetailsService {
    fun loadUserByEmail(principal: String) : UserDetails
}