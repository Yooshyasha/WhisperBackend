package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.service.JwtService
import com.yooshyasha.WhisperBackend.service.UserService
import org.springframework.http.ResponseEntity
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
}