package com.arseniomuanda.person_percistence_api.controllers

import com.arseniomuanda.person_percistence_api.domains.models.PersonModel
import com.arseniomuanda.person_percistence_api.domains.repositories.PersonRepository
import com.arseniomuanda.person_percistence_api.domains.services.PersonService
import com.arseniomuanda.person_percistence_api.dtos.CreatePerson
import jakarta.annotation.PostConstruct
import jakarta.servlet.http.HttpServletResponse
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException

@RestController
@RequestMapping("/api/people")
class PeopleController(private val personRepository: PersonRepository, private val personService: PersonService) {

    @GetMapping("/excel")
    fun getExcel(response: HttpServletResponse){
        personService.extractExcel(response)
    }

    @RequestMapping
    fun index() = personRepository.findAllByOrderByCreatedAtDesc()

    @PostMapping
    fun store(@RequestBody @Valid newPerson: CreatePerson): ResponseEntity<PersonModel> {
        return ResponseEntity.status(HttpStatus.CREATED)
            .body(personService.createPerson(newPerson))
    }

    @GetMapping("/{id}")
    fun getPerson(@PathVariable id: Long): PersonModel{
        return personRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND) }
    }
}