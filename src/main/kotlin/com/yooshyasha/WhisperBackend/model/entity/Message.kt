package com.yooshyasha.WhisperBackend.model.entity

import jakarta.persistence.*

@Entity
@Table(name = "messages")
data class Message (
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    var fromUser: User = User(),

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chat_id")
    var chat: Chat = Chat(),

    var text: String = "",
)