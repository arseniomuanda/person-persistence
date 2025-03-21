package com.arseniomuanda.person_percistence_api.domains.models

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor
import java.time.LocalDateTime


@Entity
@NoArgsConstructor
@AllArgsConstructor(staticName = "build")
class UserModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @NotBlank(message = "name cannot be blank")
    @Size(min = 3, max = 45)
    lateinit var name: String

    @Email(message = "enter a valid email address")
    @NotBlank(message = "email cannot be blank")
    lateinit var email: String

    @NotBlank(message = "password cannot be blank")
    @Size(max = 255)
    lateinit var password: String

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}