package com.arseniomuanda.person_percistence_api.domains.repositories

import com.arseniomuanda.person_percistence_api.domains.models.UserModel
import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserModel, Long> {
    fun findAllByOrderByCreatedAtDesc(): Iterable<UserModel>
    fun existsByEmail(email: String): Boolean
}