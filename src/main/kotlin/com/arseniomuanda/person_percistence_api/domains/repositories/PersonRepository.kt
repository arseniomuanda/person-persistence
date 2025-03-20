package com.arseniomuanda.person_percistence_api.domains.repositories

import com.arseniomuanda.person_percistence_api.domains.models.PersonModel
import org.springframework.data.jpa.repository.JpaRepository

interface PersonRepository: JpaRepository<PersonModel, Long> {
    fun findAllByOrderByCreatedAtDesc(): Iterable<PersonModel>
}