package com.github.hyeyoom.blog.controller.page

import com.github.hyeyoom.blog.entity.account.Account
import com.github.hyeyoom.blog.entity.account.AccountRepository
import com.github.hyeyoom.blog.entity.post.*
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.web.bind.annotation.GetMapping
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
    fun test(): String {
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
}