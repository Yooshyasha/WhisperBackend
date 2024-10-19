package com.yooshyasha.WhisperBackend.service

import com.yooshyasha.WhisperBackend.model.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.apache.commons.logging.Log
import org.apache.commons.logging.LogFactory
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {
    protected val logger: Log = LogFactory.getLog(this.javaClass)

    private val secretKey = "superSecretKey"

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.uuid.toString())
            .claim("nickname", user.nickname)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun extractUserId(token: String): UUID? {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            UUID.fromString(claims.body.subject)
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenValid(token: String): Boolean {
        return try {
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            logger.error("Token validation error: ${e.message}")
            false
        }
    }
}