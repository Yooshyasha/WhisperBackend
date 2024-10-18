package com.yooshyasha.WhisperBackend.model.entity

data class Message (
    val fromUser: User,
    val chat: Chat,
    val text: String,
)