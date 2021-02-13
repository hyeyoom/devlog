package com.github.hyeyoom.blog.controller.api

import com.github.hyeyoom.blog.aop.LoginRequired
import com.github.hyeyoom.blog.config.converter.CurrentUser
import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.service.post.PostService
import mu.KotlinLogging
import org.springframework.cache.annotation.CacheEvict
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


private val log = KotlinLogging.logger {}

@RequestMapping("/v1/posts")
@RestController
class PostApiController(
    private val postService: PostService
) {

    @LoginRequired
    @CacheEvict(cacheNames = ["posts_summary"], allEntries = true)
    @DeleteMapping("/{title}")
    fun removePost(@PathVariable title: String, @CurrentUser userDetails: UserDetails) {
        val requester = userDetails.getPrincipal()
        postService.removePost(title, requester)
        log.info("요청자 ${requester}의 게시글 제목 ${title}에 대한 삭제 요청을 완료하였습니다.")
    }
}