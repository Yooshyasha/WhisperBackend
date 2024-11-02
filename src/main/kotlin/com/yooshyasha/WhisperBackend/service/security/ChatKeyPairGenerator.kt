package com.yooshyasha.WhisperBackend.service.security

import java.security.PrivateKey
import java.security.PublicKey

interface ChatKeyPairGenerator {
    fun generateKeyPair()
    fun genPublicKey(): PublicKey
    fun genPrivateKey(): PrivateKey
}