package com.arseniomuanda.person_percistence_api.domains.services

import com.arseniomuanda.person_percistence_api.domains.models.PersonModel
import com.arseniomuanda.person_percistence_api.domains.repositories.PersonRepository
import com.arseniomuanda.person_percistence_api.domains.repositories.UserRepository
import com.arseniomuanda.person_percistence_api.dtos.CreatePerson
import com.arseniomuanda.person_percistence_api.generators.ExcelPeopleGenerator
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Service
import org.springframework.validation.annotation.Validated
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*


@Service
@Validated
class PersonService(
    private val personRepository: PersonRepository,
    private val userRepository: UserRepository,
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

    fun extractExcel(response: HttpServletResponse) {
        val excelPeopleGenerator = ExcelPeopleGenerator(
            people = personRepository.findAll()
        )

        response.contentType = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet" // Tipo MIME para Excel
        val dateFormatter: DateFormat = SimpleDateFormat("yyyy-MM-dd_HH-mm-ss") // Melhor evitar ":" no nome do arquivo
        val currentDateTime = dateFormatter.format(Date()) // Formato de data fixo para o nome do arquivo

        val headerKey = "Content-Disposition"
        val headerValue = "attachment; filename=people$currentDateTime.xlsx"
        response.setHeader(headerKey, headerValue)

        try {
            excelPeopleGenerator.generateExcelFile(response)  // Gera o arquivo Excel na resposta HTTP
            response.flushBuffer()  // Garantir que o conteúdo seja enviado imediatamente
        } catch (e: Exception) {
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR  // Código de erro 500
            response.writer.write("Erro ao gerar o arquivo Excel: ${e.message}")
            e.printStackTrace()  // ‘Log’ de erro para ajudar na depuração
        }
    }
}
