package com.yooshyasha.WhisperBackend.model.entity

data class Chat (
    val chatId: Long,
    val messages: List<Message>,
)