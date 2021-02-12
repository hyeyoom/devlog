package com.github.hyeyoom.blog

import com.github.hyeyoom.blog.service.account.SignUpService
import com.github.hyeyoom.blog.service.account.dto.BasicSignUpRequest
import com.github.hyeyoom.blog.service.post.PostService
import com.github.hyeyoom.blog.service.post.dto.PostWriteRequest
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class BlogApplication(
	val signUpService: SignUpService,
	val postService: PostService
) {
	@Bean
	fun test() {
		val bsr = BasicSignUpRequest("high.neoul@gmail.com", "hyeyoom", "1")
		signUpService.signUpBasic(bsr)

		val pwr1 = PostWriteRequest("t1", "c1", "java", "high.neoul@gmail.com")
		val pwr2 = PostWriteRequest("t2", "c2", "java", "high.neoul@gmail.com")
		val pwr3 = PostWriteRequest("t3", "c3", "java", "high.neoul@gmail.com")
		val pwr4 = PostWriteRequest("t4", "c4", "java", "high.neoul@gmail.com")
		val pwr5 = PostWriteRequest("t5", "c5", "java", "high.neoul@gmail.com")
		val pwr6 = PostWriteRequest("t6", "c6", "java", "high.neoul@gmail.com")
		val pwr7 = PostWriteRequest("t7", "c7", "java", "high.neoul@gmail.com")
		postService.write(pwr1)
		postService.write(pwr2)
		postService.write(pwr3)
		postService.write(pwr4)
		postService.write(pwr5)
		postService.write(pwr6)
		postService.write(pwr7)
	}
}

fun main(args: Array<String>) {
	runApplication<BlogApplication>(*args)
}
