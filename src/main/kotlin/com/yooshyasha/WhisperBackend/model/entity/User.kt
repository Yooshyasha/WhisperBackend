package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*
import java.util.UUID

@Entity
@Table(name = "users")
data class User (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0L,

    var uuid: UUID = UUID.randomUUID(),

    var nickname: String = "",

    @OneToMany(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    var chats: List<Chat> = emptyList(),
)