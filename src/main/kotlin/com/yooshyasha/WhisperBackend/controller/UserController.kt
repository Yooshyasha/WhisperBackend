package com.yooshyasha.WhisperBackend.controller

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.repository.UserRepository
import com.yooshyasha.WhisperBackend.service.JwtService
import com.yooshyasha.WhisperBackend.service.UserService
import com.yooshyasha.WhisperBackend.service.impl.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.core.Authentication
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
@RequestMapping("/user")
class UserController(
    private val jwtService: JwtService,
    private val userService: UserService,
) {

    @GetMapping("/getUser/{userId}")
    fun getUser(@PathVariable userId: UUID) : ResponseEntity<User> {
        val user = userService.getUser(userId)
        return if (user != null) {
            ResponseEntity.ok(user)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    @GetMapping("/getMe")
    fun getMe() : ResponseEntity<User> {
        val authentication = SecurityContextHolder.getContext().authentication
        if (authentication == null || authentication.principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()
        }

        val userDetails = authentication.principal as? UserDetailsImpl
            ?: return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build()

        val user = userService.getUser(userDetails.uuid)

        return ResponseEntity.ok(user)
    }
}