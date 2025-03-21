package com.arseniomuanda.person_percistence_api.dtos

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Pattern
import jakarta.validation.constraints.Size
import org.hibernate.validator.constraints.UniqueElements
import org.springframework.validation.annotation.Validated

@Validated
data class CreateUser(
    @NotBlank(message = "email cannot be null")
    @Email(message = "email needs to be valid")
    @UniqueElements
    var email: String,

    @NotNull(message = "name cannot be null")
    @Size(min = 3, max = 45, message = "name needs to be between 3 and 45")
    var name: String,
    @NotNull(message = "password cannot be null")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$",
        message = "password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    var password: String
)


data class UpdateUser(
    @Size(min = 3, max = 45, message = "name needs to be between 3 and 45")
    var name: String? = null,

    @Email(message = "Enter e valid email")
    var email: String? = null,

    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[@\$!%*?&])[A-Za-z\\d@\$!%*?&]{8,}\$",
        message = "password must contain at least one uppercase letter, one lowercase letter, one number, and one special character"
    )
    var password: String? = null
)
