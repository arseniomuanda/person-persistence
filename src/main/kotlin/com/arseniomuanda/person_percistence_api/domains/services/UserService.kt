package com.arseniomuanda.person_percistence_api.domains.services

import com.arseniomuanda.person_percistence_api.domains.models.UserModel
import com.arseniomuanda.person_percistence_api.domains.repositories.UserRepository
import com.arseniomuanda.person_percistence_api.dtos.CreateUser
import com.arseniomuanda.person_percistence_api.dtos.UpdateUser
import com.arseniomuanda.person_percistence_api.handlers.InvalidRequestException
import com.arseniomuanda.person_percistence_api.utils.byCrypt
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import org.springframework.web.server.ResponseStatusException

@Service
@Validated
class UserService(private val repository: UserRepository) {
    fun newUser(userDTO: CreateUser): UserModel {
        //validateRequest(userDTO)
        return repository.save(mapperToEntity(userDTO))
    }

    private fun mapperToEntity(userRequest: CreateUser): UserModel {
        val user: UserModel = UserModel()
        user.email = userRequest.email
        user.name = userRequest.name
        user.password = userRequest.password

        return user
    }

    private fun validateRequest(userRequest: CreateUser) {
        if (userRequest.name.isBlank()) {
            throw InvalidRequestException(
                "User name cannot be blank"
            )
        }
    }

    fun updateUser(newData: UpdateUser, id: Long): UserModel {
        val user = repository.findById(id).orElseThrow { throw ResponseStatusException(HttpStatus.NOT_FOUND) }
        user.name = newData.name ?: user.name
        user.password = newData.password?.byCrypt() ?: user.password
        user.email = newData.email ?: user.email

        return repository.save(user)
    }
}