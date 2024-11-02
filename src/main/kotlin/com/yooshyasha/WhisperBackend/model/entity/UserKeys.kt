package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*


@Entity
data class UserKeys(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    val user: User = User(),

    val publicKey: String = "",
)