package com.z.chatapplication.domain.model

data class Config(
    val initialCredits: Int,
    val creditDeduction: Int,
    val apiKey: String
)
