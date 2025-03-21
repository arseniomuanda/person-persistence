package com.arseniomuanda.person_percistence_api.controllers

import com.arseniomuanda.person_percistence_api.domains.models.UserModel
import com.arseniomuanda.person_percistence_api.domains.repositories.UserRepository
import com.arseniomuanda.person_percistence_api.domains.services.UserService
import com.arseniomuanda.person_percistence_api.dtos.CreateUser
import com.arseniomuanda.person_percistence_api.dtos.UpdateUser
import com.arseniomuanda.person_percistence_api.utils.byCrypt
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.Errors
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException


@RestController
@RequestMapping("/api/users")
class UserController(
    private val userRepository: UserRepository,
    private val userService: UserService
) {
    @RequestMapping
    fun index() = userRepository.findAllByOrderByCreatedAtDesc()


    @PostMapping
    fun store(@RequestBody @Valid newUser: CreateUser): ResponseEntity<UserModel> {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.newUser(newUser))
    }

    @PutMapping("/{id}")
    @ResponseBody
    fun update(@PathVariable id: Long, @RequestBody @Valid newUser: UpdateUser, errors: Errors): ResponseEntity<UserModel> {
        return ResponseEntity.status(HttpStatus.OK).body(userService.updateUser(newUser, id))
    }


}