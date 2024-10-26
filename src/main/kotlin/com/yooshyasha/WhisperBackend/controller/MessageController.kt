package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.Message
import com.yooshyasha.WhisperBackend.service.MessageService
import org.springframework.http.ResponseEntity
import org.springframework.messaging.core.GenericMessagingTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/message")
class MessageController(
    private val messageService: MessageService,
    private val messagingTemplate: SimpMessagingTemplate
) {

    @PostMapping("/sendMessage")
    fun sendMessage(@RequestBody message: Message) : ResponseEntity<Message> {
        messageService.saveMessage(message)

        messagingTemplate.convertAndSend("/chat/topic/messages/${message.chat.chatId}")

        return ResponseEntity.ok(message)
    }

}