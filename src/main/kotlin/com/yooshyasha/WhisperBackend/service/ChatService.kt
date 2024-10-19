package com.yooshyasha.WhisperBackend.service

import com.yooshyasha.WhisperBackend.model.entity.Chat
import com.yooshyasha.WhisperBackend.repository.ChatRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class ChatService(
    private val chatRepository: ChatRepository
) {

    @Transactional
    fun saveChat(chat: Chat) {
        chatRepository.save(chat)
    }

    @Transactional
    fun deleteChat(chat: Chat) {
        chatRepository.delete(chat)
    }
}