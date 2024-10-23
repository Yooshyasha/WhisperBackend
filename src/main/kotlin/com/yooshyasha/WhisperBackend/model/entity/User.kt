package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
data class User (
    @Id
    var uuid: UUID = UUID.randomUUID(),

    @Column(unique = true)
    var nickname: String = "",

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chats_id")
    var chats: List<Chat> = emptyList(),
)