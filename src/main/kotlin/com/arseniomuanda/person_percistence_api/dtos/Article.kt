package com.arseniomuanda.person_percistence_api.dtos

import com.arseniomuanda.person_percistence_api.utils.toSlug
import java.time.LocalDateTime

data class Article(
    var title: String,
    var content: String,
    var author: String,
    var createdAt: LocalDateTime = LocalDateTime.now(),
    var slug: String = title.toSlug(),
)

data class ArticleUptdate(
     val title: String?,
     val content: String?,
     val author: String?,
)