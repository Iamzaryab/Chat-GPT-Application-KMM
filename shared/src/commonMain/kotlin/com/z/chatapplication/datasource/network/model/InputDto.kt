package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Request body for POST /v1/chat/completions. */
@Serializable
data class InputDto(
    @SerialName("model")
    val model: String,
    @SerialName("messages")
    val messages: List<ChatMessageDto>,
    @SerialName("max_completion_tokens")
    val max_completion_tokens: Int
)
