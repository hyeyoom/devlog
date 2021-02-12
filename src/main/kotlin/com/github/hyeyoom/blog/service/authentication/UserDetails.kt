package com.github.hyeyoom.blog.service.authentication

interface UserDetails {
    fun getPrincipal(): String
    fun getCredentials(): String
    fun checkCredentials(credential: String): Boolean
}