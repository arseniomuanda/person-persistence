package com.arseniomuanda.person_percistence_api.dtos

import jakarta.validation.constraints.*

data class CreatePerson(
    @field:NotBlank(message = "O nome completo é obrigatório.")
    val fullName: String,

    @field:NotNull(message = "A data de nascimento é obrigatória.")
    @field:Pattern(regexp = "\\d{4}-\\d{2}-\\d{2}", message = "A data deve estar no formato AAAA-MM-DD.")
    val birthDate: String,

    @field:NotBlank(message = "A cidade de nascimento é obrigatória.")
    val birthCity: String,

    @field:NotBlank(message = "A cidade atual é obrigatória.")
    val city: String,

    @field:NotBlank(message = "O país é obrigatório.")
    val country: String,

    @field:NotBlank(message = "O telefone é obrigatório.")
    @field:Pattern(regexp = "\\+?[0-9]{10,15}", message = "O telefone deve conter entre 10 a 15 dígitos.")
    val phone: String,

    @field:NotBlank(message = "O número de identificação é obrigatório.")
    val idNumber: String,

    @field:Size(max = 9, message = "O número do passaporte não pode ter mais de 9 caracteres.")
    val passport: String? = null,

    @field:NotNull(message = "O ID do usuário é obrigatório.")
    val userId: Long
)
