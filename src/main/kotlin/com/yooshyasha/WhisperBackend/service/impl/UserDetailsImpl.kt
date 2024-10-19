package com.yooshyasha.WhisperBackend.service.impl

import com.yooshyasha.WhisperBackend.model.entity.User
import com.yooshyasha.WhisperBackend.service.UserService
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.stereotype.Component
import java.util.UUID

@Component
class UserDetailsImpl(
    private val userService: UserService
) : UserDetails {
    private lateinit var user: User

    val uuid: UUID
        get() = user.uuid

    override fun getAuthorities(): Collection<GrantedAuthority> {
        val authorities = mutableListOf<GrantedAuthority>()

        authorities.add(SimpleGrantedAuthority("ROLE_USER"))

        return authorities
    }

    override fun getPassword(): String? {
        return null
    }

    override fun getUsername(): String {
        return user.nickname
    }

    override fun isAccountNonExpired(): Boolean {
        return true // или ваша логика
    }

    override fun isAccountNonLocked(): Boolean {
        return true // или ваша логика
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true // или ваша логика
    }

    override fun isEnabled(): Boolean {
        return true // или ваша логика
    }

    fun loadUserById(userId: UUID): UserDetails {
        user = userService.getUser(userId)!!
        return this
    }
}
