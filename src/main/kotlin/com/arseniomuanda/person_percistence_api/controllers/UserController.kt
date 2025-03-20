package com.arseniomuanda.person_percistence_api.controllers

import com.arseniomuanda.person_percistence_api.domains.models.UserModel
import com.arseniomuanda.person_percistence_api.domains.repositories.UserRepository
import com.arseniomuanda.person_percistence_api.dtos.CreateUser
import com.arseniomuanda.person_percistence_api.dtos.UpdateUser
import com.arseniomuanda.person_percistence_api.utils.byCrypt
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/users")
class UserController(private val repository: UserRepository) {

    @RequestMapping
    fun index() = repository.findAllByOrderByCreatedAtDesc()

    @PostMapping
    fun store(@RequestBody newUser: UserModel): UserModel {
        newUser.password = newUser.password.byCrypt()
        return repository.save(newUser)
    }

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @RequestBody newUser: UpdateUser): UserModel {
        var user = repository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        user.name = newUser.name ?: user.name
        user.password = newUser.password?.byCrypt() ?: user.password
        user.email = newUser.email ?: user.email

        return repository.save(user)
    }


}