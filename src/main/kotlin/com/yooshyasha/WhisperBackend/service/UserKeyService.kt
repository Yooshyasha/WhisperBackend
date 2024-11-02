package com.yooshyasha.WhisperBackend.service

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.model.entity.UserKeys
import com.yooshyasha.WhisperBackend.repository.UserKeysRepository
import org.springframework.stereotype.Service

@Service
class UserKeyService(private val userKeysRepository: UserKeysRepository) {

    fun savePublicKey(user: User, publicKey: String) {
        val userKey = UserKeys(user = user, publicKey = publicKey)
        userKeysRepository.save(userKey)
    }

    fun getPublicKey(userId: Long): String? {
        return userKeysRepository.findByUserId(userId)?.publicKey
    }
}