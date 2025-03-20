package com.arseniomuanda.person_percistence_api.domains.models

import jakarta.persistence.*
import java.time.LocalDateTime


@Entity
class UserModel() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
    lateinit var name: String
    lateinit var email: String
    lateinit var password: String

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}