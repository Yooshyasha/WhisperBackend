package com.yooshyasha.WhisperBackend.service.security

import java.security.PrivateKey
import java.security.PublicKey
import java.util.*
import javax.crypto.Cipher

class ChatSecurityService : ChatSecurity {
    override fun encryptMessage(msg: String, publicKey: PublicKey): String {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.ENCRYPT_MODE, publicKey)
        val encryptedBytes = cipher.doFinal(msg.toByteArray())
        return Base64.getEncoder().encodeToString(encryptedBytes)
    }

    override fun decryptMessage(msg: String, privateKey: PrivateKey): String {
        val cipher = Cipher.getInstance("RSA")
        cipher.init(Cipher.DECRYPT_MODE, privateKey)
        val decryptedBytes = cipher.doFinal(Base64.getDecoder().decode(msg))
        return String(decryptedBytes)
    }
}