package com.yooshyasha.WhisperBackend.service.security

import org.springframework.stereotype.Service
import java.security.PrivateKey
import java.security.PublicKey

@Service
interface ChatSecurity {
    fun encryptMessage(msg: String, publicKey: PublicKey): String
    fun decryptMessage(msg: String, privateKey: PrivateKey): String
}