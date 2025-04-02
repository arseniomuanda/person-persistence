package com.arseniomuanda.person_percistence_api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping("/")
class HomeController {
    @GetMapping()
    fun index(): ModelAndView {
        val modelAndView = ModelAndView()
        modelAndView.viewName = "index"

        // Adicionando dados ao modelo (opcional)
        modelAndView.addObject("message", "Bem-vindo ao índice!")

        return modelAndView
    }
}