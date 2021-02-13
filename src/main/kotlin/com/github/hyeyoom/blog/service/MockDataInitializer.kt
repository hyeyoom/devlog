package com.github.hyeyoom.blog.service

import com.github.hyeyoom.blog.service.account.SignUpService
import com.github.hyeyoom.blog.service.account.dto.BasicSignUpRequest
import com.github.hyeyoom.blog.service.post.PostService
import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Profile("default")
@Configuration
class MockDataInitializer(
    val signUpService: SignUpService,
    val postService: PostService
) {

    @Bean
    fun init() {
        val bsr = BasicSignUpRequest("high.neoul@gmail.com", "hyeyoom", "1")
        signUpService.signUpBasic(bsr)

        val pwr1 = PostWriteRequest("t1", "s1", "content1", "java", "high.neoul@gmail.com")
        val pwr2 = PostWriteRequest("t2", "s2", "content2", "java", "high.neoul@gmail.com")
        val pwr3 = PostWriteRequest("t3", "s3", "content3", "java", "high.neoul@gmail.com")
        val pwr4 = PostWriteRequest("t4", "s4", "content4", "java", "high.neoul@gmail.com")
        val pwr5 = PostWriteRequest("t5", "s5", "content5", "java", "high.neoul@gmail.com")
        val pwr6 = PostWriteRequest("t6", "s6", "content6", "java", "high.neoul@gmail.com")
        val pwr7 = PostWriteRequest("t7", "s7", "content7", "java", "high.neoul@gmail.com")
        postService.write(pwr1)
        postService.write(pwr2)
        postService.write(pwr3)
        postService.write(pwr4)
        postService.write(pwr5)
        postService.write(pwr6)
        postService.write(pwr7)
    }
}