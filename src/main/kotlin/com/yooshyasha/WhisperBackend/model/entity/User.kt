package com.yooshyasha.WhisperBackend.model.entity

import java.util.UUID

data class User (
    val id: UUID,
    val nickname: String
)