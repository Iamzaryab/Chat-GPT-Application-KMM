package com.z.chatapplication.domain.model


data class Usage(
    val promptTokens: Int,
    val completionTokens: Int,
    val totalTokens: Int
)