package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** One message in a Chat Completions request or response. */
@Serializable
data class ChatMessageDto(
    @SerialName("role")
    val role: String,
    @SerialName("content")
    val content: String? = null
) {
    companion object {
        const val ROLE_USER = "user"
        const val ROLE_ASSISTANT = "assistant"
    }
}
