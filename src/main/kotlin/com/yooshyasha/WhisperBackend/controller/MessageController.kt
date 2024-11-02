package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.Message
import com.yooshyasha.WhisperBackend.service.MessageService
import com.yooshyasha.WhisperBackend.service.UserKeyService
import com.yooshyasha.WhisperBackend.service.UserService
import org.springframework.http.ResponseEntity
import org.springframework.messaging.core.GenericMessagingTemplate
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/message")
class MessageController(
    private val messageService: MessageService,
    private val messagingTemplate: SimpMessagingTemplate,
    private val userService: UserService,
    private val userKeyService: UserKeyService,
) {

    @GetMapping("/publicKey")
    fun getPublicKey(userUUID: UUID) : ResponseEntity<String> {
        val user = userService.getUser(userUUID)

        if (user != null) {
            val publicKey = userKeyService.getPublicKey(user)

            return if (publicKey != null) {
                ResponseEntity.ok(publicKey)
            } else ResponseEntity.notFound().build()
        }

        return ResponseEntity.notFound().build()
    }

    @PostMapping("/sendMessage")
    fun sendMessage(@RequestBody message: Message) : ResponseEntity<Message> {
        messageService.saveMessage(message)

        messagingTemplate.convertAndSend("/chat/topic/messages/${message.chat.chatId}")

        return ResponseEntity.ok(message)
    }

}