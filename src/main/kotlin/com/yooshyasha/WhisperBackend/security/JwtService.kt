package com.yooshyasha.WhisperBackend.security

import com.yooshyasha.WhisperBackend.model.entity.User
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Service
import java.util.*

@Service
class JwtService {

    private val secretKey = "superSecretKey"

    fun generateToken(user: User): String {
        return Jwts.builder()
            .setSubject(user.id.toString())
            .claim("nickname", user.nickname)
            .setIssuedAt(Date())
            .setExpiration(Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
            .signWith(SignatureAlgorithm.HS256, secretKey)
            .compact()
    }

    fun extractUserId(token: String): UUID? {
        return try {
            UUID.fromString(Jwts.parser().setSigningKey(secretKey)
                .parseClaimsJwt(token)
                .body.subject)
        } catch (e: Exception) {
            null
        }
    }

    fun isTokenValid(token: String): Boolean {
        return try{
            val claims = Jwts.parser().setSigningKey(secretKey).parseClaimsJwt(token)
            !claims.body.expiration.before(Date())
        } catch (e: Exception) {
            false
        }
    }
}