package com.github.hyeyoom.blog.service.post

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.post.Category
import com.github.hyeyoom.blog.entity.post.Post
import com.github.hyeyoom.blog.entity.post.PostRepository
import com.github.hyeyoom.blog.service.account.AccountQueryService
import com.github.hyeyoom.blog.service.exception.NoResourceFoundException
import com.github.hyeyoom.blog.service.post.dto.CategoryDto
import com.github.hyeyoom.blog.service.post.dto.PostDto
import com.github.hyeyoom.blog.service.post.dto.PostSummary
import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import mu.KotlinLogging
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.stream.Collectors

private val log = KotlinLogging.logger {}

@Service
@Transactional(readOnly = true)
class PostService(
    private val repository: PostRepository,
    private val categoryService: CategoryService,
    private val accountQueryService: AccountQueryService
) {

    @Cacheable(cacheNames = ["post"], key = "#title")
    fun getPost(title: String): PostDto {
        val post = repository.findByTitle(title) ?: throw NoResourceFoundException("페이지가 없다")
        return makePostDto(post)
    }

    @Transactional
    @CacheEvict(cacheNames = ["post"], key = "#title")
    fun removePost(title: String, requester: String) {
        val post = repository.findByTitle(title) ?: throw NoResourceFoundException("지울 페이지가 없다")
        if(post.account.email != requester) {
            throw IllegalStateException("타인의 게시글을 삭제하려고 함")
        }
        repository.delete(post)
    }

    @Cacheable(cacheNames = ["posts_summary"])
    fun getSummaries(): MutableList<PostSummary> {
        val pages = repository.findPosts(PageRequest.of(0, 5))
        return pages.stream()
            .map { PostSummary.toDto(it) }
            .collect(Collectors.toList())
    }

    @CacheEvict(cacheNames = ["posts_summary"], allEntries = true)
    @Transactional
    fun write(request: PostWriteRequest) {
        val category = findCategory(request.category)
        val account = findAccount(request.email)
        val post = Post(
            title = request.title, summary = request.summary,
            content = request.content, account = account,
            category = category
        )
        repository.save(post)
    }

    private fun makePostDto(post: Post) = PostDto(
        post.title, post.content,
        CategoryDto(post.category.name), mutableListOf(),
        post.createdDate, post.lastModifiedDate
    )

    private fun findCategory(name: String): Category {
        return categoryService.findCategory(name) ?: categoryService.createNewCategory(name)
    }

    private fun findAccount(email: String): Account {
        return accountQueryService.findByEmail(email)
            ?: throw IllegalStateException("No such user.")
    }
}