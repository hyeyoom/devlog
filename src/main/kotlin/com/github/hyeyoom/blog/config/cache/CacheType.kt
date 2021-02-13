package com.github.hyeyoom.blog.config.cache

enum class CacheType(
    val cacheName: String,
    val expiredAfterWrite: Long,
    val maximumSize: Long
) {
    POSTS_SUMMARY("posts_summary", 1 * 60, 1000),
    POST("post", 1 * 60, 1000),
}