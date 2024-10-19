package com.yooshyasha.WhisperBackend.service

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.repository.UserRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService(
    private val userRepository: UserRepository
) {

    @Transactional
    fun saveUser(user: User) {
        userRepository.save(user)
    }

    @Transactional
    fun deleteUser(user: User) {
        userRepository.delete(user)
    }

    @Transactional
    fun getUser(userId: UUID) : User? {
        return userRepository.findById(userId).orElse(null)
    }
}