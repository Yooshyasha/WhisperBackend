package com.yooshyasha.WhisperBackend.service

import com.yooshyasha.WhisperBackend.model.entity.Message
import com.yooshyasha.WhisperBackend.repository.MessageRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

@Service
class MessageService(
    private val messageRepository: MessageRepository
) {

    @Transactional
    fun saveMessage(message: Message) {
        messageRepository.save(message)
    }

    @Transactional
    fun deleteMessage(message: Message) {
        messageRepository.delete(message)
    }
}