package com.arseniomuanda.person_percistence_api

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class PersonPersistenceApiApplication
{

}

fun main(args: Array<String>) {
	runApplication<PersonPersistenceApiApplication>(*args)
	//SpringApplication.run(PersonPersistenceApiApplication::class.java, *args)
}
