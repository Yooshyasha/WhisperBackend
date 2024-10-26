package com.yooshyasha.WhisperBackend.repository

import com.yooshyasha.WhisperBackend.model.entity.Chat
import com.yooshyasha.WhisperBackend.model.entity.Message
import com.yooshyasha.WhisperBackend.model.entity.User
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ChatRepository : JpaRepository<Chat, Long>

interface MessageRepository : JpaRepository<Message, Long>

interface UserRepository : JpaRepository<User, UUID> {
    fun findByNickname(nickname: String) : User?
}
