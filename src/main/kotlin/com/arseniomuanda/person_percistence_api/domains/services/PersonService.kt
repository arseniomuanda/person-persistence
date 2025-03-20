package com.arseniomuanda.person_percistence_api.domains.services

import com.arseniomuanda.person_percistence_api.domains.models.PersonModel
import com.arseniomuanda.person_percistence_api.domains.repositories.PersonRepository
import com.arseniomuanda.person_percistence_api.domains.repositories.UserRepository
import com.arseniomuanda.person_percistence_api.dtos.CreatePerson
import org.springframework.stereotype.Service

@Service
class PersonService(
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository
) {

    fun createPerson(createPerson: CreatePerson): PersonModel {
        // Buscar o usuário associado ao userId (se necessário)
        val user = userRepository.findById(createPerson.userId)
            .orElseThrow { IllegalArgumentException("Usuário não encontrado") }

        // Mapear o DTO para a entidade
        val person = PersonModel().apply {
            fullName = createPerson.fullName
            birthDate = createPerson.birthDate
            birthCity = createPerson.birthCity
            city = createPerson.city
            country = createPerson.country
            phone = createPerson.phone
            idNumber = createPerson.idNumber
            passport = createPerson.passport
            this.user = user
        }

        // Salvar a entidade no banco
        return personRepository.save(person)
    }
}
