package com.arseniomuanda.person_percistence_api.dtos

data class CreateUser(
    var name: String,
    var email: String,
    var password: String
)

data class UpdateUser(
    var name: String? = null,
    var email: String? = null,
    var password: String? = null
)
