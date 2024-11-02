package com.yooshyasha.WhisperBackend.repository

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.model.entity.UserKeys
import org.springframework.data.jpa.repository.JpaRepository

interface UserKeysRepository : JpaRepository<UserKeys, Long> {
    fun findByUser(user: User): UserKeys?
}