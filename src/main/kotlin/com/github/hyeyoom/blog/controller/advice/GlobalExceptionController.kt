package com.github.hyeyoom.blog.controller.advice

import mu.KotlinLogging
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView
import org.springframework.web.servlet.NoHandlerFoundException
import javax.servlet.http.HttpServletRequest

private val log = KotlinLogging.logger {}

@ControllerAdvice
class GlobalExceptionController {

    @ExceptionHandler(NoHandlerFoundException::class)
    fun handlePageNotFound(request: HttpServletRequest, e: Exception): ModelAndView {
        log.info("wrong path: ${request.requestURL}")
        val mav = ModelAndView()
        mav.viewName = "error/404"
        return mav
    }

    @ExceptionHandler(Error::class)
    fun handleInternalServerError(request: HttpServletRequest, e: Exception): ModelAndView {
        val mav = ModelAndView()
        log.error(e.message, e)
        mav.viewName = "error/500"
        return mav
    }
}