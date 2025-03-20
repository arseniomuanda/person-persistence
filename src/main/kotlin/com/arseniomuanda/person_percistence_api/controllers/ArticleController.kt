package com.arseniomuanda.person_percistence_api.controllers

import com.arseniomuanda.person_percistence_api.dtos.Article
import com.arseniomuanda.person_percistence_api.dtos.ArticleUptdate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/articles")
class ArticleController {

    val articles: MutableList<Article> = mutableListOf(
        Article(
            title = "Article 1",
            author = "Arsenio Muanda",
            content = "Eu escrevi esse artigo para poder testar o kotlin spring boot",
        )
    )

    @RequestMapping
    fun articles() = articles

    @RequestMapping("/{slug}")
    fun articles(@PathVariable slug: String) = articles.find { it.slug == slug } ?: throw ResponseStatusException(
        HttpStatus.NOT_FOUND
    )

    @PostMapping
    fun newArticle(@RequestBody article: Article): Article {
        articles.add(article)
        return article
    }

    @PutMapping("/{slug}")
    fun updateArticle(@RequestBody article: ArticleUptdate,@PathVariable slug: String): Article {

        val oldArticle = articles.find { it.slug == slug } ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
        oldArticle.author = article.author ?: oldArticle.content
        oldArticle.content = article.content ?: oldArticle.content
        return oldArticle
    }

    @DeleteMapping("/{slug}")
    fun deleteArticle(@PathVariable slug: String) {
        articles.removeIf {  it.slug == slug }
    }
}