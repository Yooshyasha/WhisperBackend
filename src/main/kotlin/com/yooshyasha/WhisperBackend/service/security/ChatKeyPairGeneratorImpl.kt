package com.yooshyasha.WhisperBackend.service.security

import org.springframework.stereotype.Service
import java.security.KeyPair
import java.security.KeyPairGenerator
import java.security.PrivateKey
import java.security.PublicKey

@Service
class ChatKeyPairGeneratorImpl : ChatKeyPairGenerator {
    private lateinit var keyPair: KeyPair

    init {
        generateKeyPair()
    }

    final override fun generateKeyPair() {
        val keyGen = KeyPairGenerator.getInstance("RSA")
        keyGen.initialize(2048)
        keyPair = keyGen.generateKeyPair()
    }

    override fun genPublicKey(): PublicKey {
        return keyPair.public
    }

    override fun genPrivateKey(): PrivateKey {
        return keyPair.private
    }
}