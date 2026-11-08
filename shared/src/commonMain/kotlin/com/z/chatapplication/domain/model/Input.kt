package com.z.chatapplication.domain.model


data class Input(
    val model: String,
    val messages: List<Message>,
    val maxTokens: Int
)
