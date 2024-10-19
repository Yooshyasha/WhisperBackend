package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.service.JwtService
import com.yooshyasha.WhisperBackend.service.UserService
import com.yooshyasha.WhisperBackend.service.impl.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.RequestEntity
import org.springframework.http.ResponseEntity
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

data class RegistrationRequest(val nickname: String)

@RestController
@RequestMapping("/auth")
class AuthController(
    private val jwtService: JwtService,
    private val userService: UserService,
) {

    @PostMapping("/register")
    fun register(@RequestBody request: RegistrationRequest) : ResponseEntity<Map<String, String>> {
        /*
        * Returned token. For use:
        * HEADERS: Authorization: Bearer $token
        */
        val user = userService.createUser(nickname = request.nickname)

        val token = jwtService.generateToken(user)

        return ResponseEntity.ok(mapOf("token" to token))
    }

    @PostMapping("/isAuth")
    fun isAuth() : ResponseEntity<Boolean> {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || authentication.principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        if (authentication.principal !is UserDetailsImpl) return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        return ResponseEntity.ok(true)
    }
}