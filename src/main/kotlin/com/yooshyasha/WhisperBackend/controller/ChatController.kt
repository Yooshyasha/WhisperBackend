package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.Message
import com.yooshyasha.WhisperBackend.service.MessageService
import com.yooshyasha.WhisperBackend.service.UserService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.web.bind.annotation.RestController

@RestController("/chat")
class ChatController {

    @MessageMapping("/sendMessage")
    @SendTo("/topic/messages")
    fun broadcastMessage(message: Message) : Message {
        if (message.text.isBlank()) {
            throw IllegalArgumentException("Сообщение не должно быть пустым")
        }

        return message
    }
}