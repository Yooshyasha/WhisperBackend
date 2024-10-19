package com.yooshyasha.WhisperBackend.security

import com.yooshyasha.WhisperBackend.service.JwtService
import com.yooshyasha.WhisperBackend.service.impl.UserDetailsImpl
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter (
    private val jwtService: JwtService,
    private val userDetailService: UserDetailsImpl
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val authorizationHeader = request.getHeader("Authorization")

        if ((authorizationHeader == null) || !authorizationHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response)
            return
        }

        val token = authorizationHeader.substring(7)
        val userId = jwtService.extractUserId(token)

        logger.info("$token token - ${jwtService.isTokenValid(token)}")
        logger.info("UserId - $userId")
        if (userId != null && jwtService.isTokenValid(token)) {
            logger.info("Valid token. UserId: $userId")
            val userDetails = userDetailService.loadUserById(userId)
            val authentication = UsernamePasswordAuthenticationToken(userDetails, null, userDetails.authorities)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

}