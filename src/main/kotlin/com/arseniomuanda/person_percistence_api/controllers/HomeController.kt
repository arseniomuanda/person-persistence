package com.arseniomuanda.person_percistence_api.controllers

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/")
class HomeController {
    @GetMapping()
    fun index(): String = "<h1>Welcome back your application</h1>"
}