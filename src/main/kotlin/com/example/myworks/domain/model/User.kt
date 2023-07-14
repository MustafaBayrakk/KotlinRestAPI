package com.example.myworks.domain.model

import jakarta.persistence.*

@Entity
@Table(name = "users")
data class User(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    var name: String? = null,

    @Column(name = "email" )
    var email: String? = null,
)