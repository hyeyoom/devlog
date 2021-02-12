package com.github.hyeyoom.blog.service.account.dto

import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.utils.EncryptionHashUtil

data class BasicUserDetails(
    private val email: String,
    private val password: String
):UserDetails {

    override fun getPrincipal(): String {
        return email
    }

    override fun getCredentials(): String {
        return password
    }

    override fun checkCredentials(credential: String): Boolean {
        return EncryptionHashUtil.checkEncryptionValidation(credential, password)
    }
}