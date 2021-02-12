package com.github.hyeyoom.blog.service.post

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.post.Category
import com.github.hyeyoom.blog.entity.post.Post
import com.github.hyeyoom.blog.entity.post.PostRepository
import com.github.hyeyoom.blog.service.account.AccountQueryService
import com.github.hyeyoom.blog.service.post.dto.PostDto
import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import mu.KotlinLogging
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

private val log = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class PostService(
    private val repository: PostRepository,
    private val categoryService: CategoryService,
    private val accountQueryService: AccountQueryService
) {

    fun getPosts(): MutableList<PostDto> {
        val pages = repository.findPosts(PageRequest.of(0, 5))
        for (page in pages) {
            log.info("page: $page")
        }
        return mutableListOf()
    }

    @Transactional
    fun write(request: PostWriteRequest) {
        val category = findCategory(request.category)
        val account = findAccount(request.email)
        val post = Post(
            title = request.title, content = request.content,
            account = account, category = category
        )
        repository.save(post)
    }

    private fun findCategory(name: String): Category {
        return categoryService.findCategory(name) ?: categoryService.createNewCategory(name)
    }

    private fun findAccount(email: String): Account {
        return accountQueryService.findByEmail(email)
            ?: throw IllegalStateException("No such user.")
    }
}