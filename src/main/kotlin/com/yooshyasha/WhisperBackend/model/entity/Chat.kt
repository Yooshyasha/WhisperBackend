package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "chats")
data class Chat (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var chatId: Long = 0,

    @OneToMany(mappedBy = "chat", cascade = [CascadeType.ALL], fetch = FetchType.LAZY)
    var messages: List<Message> = emptyList(),
)