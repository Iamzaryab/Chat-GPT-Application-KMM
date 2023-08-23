package com.z.chatapplication.datasource.network

import com.z.chatapplication.domain.model.Chat
import com.z.chatapplication.domain.model.Input

interface ChatService {
    suspend fun queryChat(
        input: Input,
        apiKey: String
    ): Chat
}