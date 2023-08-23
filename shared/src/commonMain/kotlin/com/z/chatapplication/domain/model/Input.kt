package com.z.chatapplication.domain.model


data class Input(
    val model: String,
    val prompt: String,
    val temperature: Int,
    val maxTokens: Int
)
