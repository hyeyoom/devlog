package com.github.hyeyoom.blog.controller.page

import com.github.hyeyoom.blog.aop.LoginRequired
import com.github.hyeyoom.blog.config.converter.CurrentUser
import com.github.hyeyoom.blog.controller.page.form.SignInForm
import com.github.hyeyoom.blog.controller.page.form.SignUpForm
import com.github.hyeyoom.blog.controller.page.form.WritePostForm
import com.github.hyeyoom.blog.entity.account.AccountRepository
import com.github.hyeyoom.blog.entity.post.CategoryRepository
import com.github.hyeyoom.blog.entity.post.PostRepository
import com.github.hyeyoom.blog.entity.post.TagRepository
import com.github.hyeyoom.blog.service.account.SignUpService
import com.github.hyeyoom.blog.service.authentication.UserDetails
import com.github.hyeyoom.blog.service.post.CategoryService
import com.github.hyeyoom.blog.service.post.PostService
import mu.KotlinLogging
import org.springframework.stereotype.Controller
import org.springframework.transaction.annotation.Transactional
import org.springframework.ui.Model
import org.springframework.validation.BindingResult
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import javax.validation.Valid

private val log = KotlinLogging.logger {}

@Transactional
@Controller
@RequestMapping("/")
class IndexController(
    private val postRepository: PostRepository,
    private val accountRepository: AccountRepository,
    private val categoryRepository: CategoryRepository,
    private val tagRepository: TagRepository,
    private val categoryService: CategoryService,
    private val postService: PostService,
    private val signUpService: SignUpService
) {

    @GetMapping
    fun main(@CurrentUser details: UserDetails?, model: Model): String {
        log.info(details?.getPrincipal())
        val posts = postService.getPosts()

        /*val account = Account("a@neoul.wiki", "hyeyoom")
        accountRepository.save(account)
        val tag = Tag("Java")
        tagRepository.save(tag)
        val category = Category("programming")
        val c1 = Category("java")
        categoryRepository.save(category)
        categoryRepository.save(c1)
        val post =
            Post(title = "this is title", content = "this is content of post", account = account, category = category)
        postRepository.save(post)

        post.addTag(tag)*/
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

    @GetMapping("/signup")
    fun signUpPage(model: Model): String {
        val signUpForm = SignUpForm()
        model.addAttribute("signUpForm", signUpForm)
        return "signup"
    }

    @PostMapping("/signup")
    fun signUp(@Valid form: SignUpForm, bindingResult: BindingResult): String {
        log.info("form: $form")
        if (bindingResult.hasFieldErrors()) {
            return "signup"
        }
        signUpService.signUpBasic(form.toRequest())
        return "redirect:/"
    }

    @GetMapping("/signin")
    fun signInPage(model: Model): String {
        val signInForm = SignInForm()
        model.addAttribute("signInForm", signInForm)
        return "signin"
    }

    @GetMapping("/tags")
    fun tags(): String {
        return "tag"
    }

    @GetMapping("/tags/{tag}")
    fun tag(@PathVariable tag: String): String {
        return "tag"
    }

    @LoginRequired
    @GetMapping("/write")
    fun write(model: Model): String {
        val categories = categoryService.getCategories()
        val writePostForm = WritePostForm()
        model.addAttribute("categories", categories)
        model.addAttribute("writePostForm", writePostForm)
        return "write"
    }

    @LoginRequired
    @PostMapping("/write")
    fun writePost(
        @Valid form: WritePostForm,
        bindingResult: BindingResult,
        @CurrentUser details: UserDetails?
    ): String {
        log.info("form: $form")
        if (bindingResult.hasFieldErrors()) {
            return "write"
        }
        postService.write(form.toRequest(details?.getPrincipal()))
        return "redirect:/"
    }

    @GetMapping("/posts/{title}")
    fun viewPost(@PathVariable title: String): String {
        return "post"
    }

}