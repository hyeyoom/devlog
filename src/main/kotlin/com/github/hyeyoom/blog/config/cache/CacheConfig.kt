package com.github.hyeyoom.blog.config.cache

import com.github.benmanes.caffeine.cache.Caffeine
import org.springframework.cache.CacheManager
import org.springframework.cache.caffeine.CaffeineCache
import org.springframework.cache.support.SimpleCacheManager
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import java.util.concurrent.TimeUnit

@Configuration
class CacheConfig {

    @Bean
    fun cacheManager(): CacheManager {
        val cacheManager = SimpleCacheManager()
        val caches = CacheType.values().map {
            CaffeineCache(
                it.cacheName,
                Caffeine.newBuilder().recordStats()
                    .expireAfterWrite(it.expiredAfterWrite, TimeUnit.SECONDS)
                    .maximumSize(it.maximumSize)
                    .build()
            )
        }
        cacheManager.setCaches(caches)
        return cacheManager
    }
}