package com.arseniomuanda.person_percistence_api.domains.models

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
class PersonModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    lateinit var fullName: String
    lateinit var birthDate: String
    lateinit var birthCity: String
    lateinit var city: String
    lateinit var country: String
    lateinit var phone: String
    lateinit var idNumber: String
    var passport: String? = null

    @OneToOne
    @JoinColumn(name = "user_id")
    lateinit var user: UserModel

    @Column(name = "created_at")
    var createdAt: LocalDateTime = LocalDateTime.now()
}