package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: UUID = UUID.randomUUID(),

    var nickname: String = "",
)