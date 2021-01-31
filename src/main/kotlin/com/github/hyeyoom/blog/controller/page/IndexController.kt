package com.github.hyeyoom.blog.controller.page

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.account.AccountRepository
import com.github.hyeyoom.blog.entity.post.*
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping

@Transactional
@Controller
@RequestMapping("/")
class IndexController(
    private val postRepository: PostRepository,
    private val accountRepository: AccountRepository,
    private val categoryRepository: CategoryRepository,
    private val tagRepository: TagRepository,
) {

    @GetMapping
    fun main(): String {
        val account = Account("a@neoul.wiki", "hyeyoom")
        accountRepository.save(account)
        val tag = Tag("Java")
        tagRepository.save(tag)
        val category = Category("programming")
        categoryRepository.save(category)
        val post = Post("this is title", "this is content of post", account, category)
        postRepository.save(post)

        post.addTag(tag)
        return "index"
    }

    @GetMapping("/pages")
    fun pages(): String {
        return "page"
    }

    @GetMapping("/pages/{page}")
    fun page(@PathVariable page: Long): String {
        return "page"
    }

    @GetMapping("/categories")
    fun categories(): String {
        return "category"
    }

    @GetMapping("/categories/{category}")
    fun category(@PathVariable category: String): String {
        return "category"
    }

    @GetMapping("/tags")
    fun tags(): String {
        return "tag"
    }

    @GetMapping("/tags/{tag}")
    fun tag(@PathVariable tag: String): String {
        return "tag"
    }

    @GetMapping("/write")
    fun writePost(): String {
        return "write"
    }

    @GetMapping("/posts/{title}")
    fun viewPost(@PathVariable title: String): String {
        return "post"
    }

}