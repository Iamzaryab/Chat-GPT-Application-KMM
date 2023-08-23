package com.z.chatapplication.presentation.chat

import com.z.chatapplication.domain.model.Input

sealed class ChatEvents {
    data class GotMessage(val message: String, val from: Int) : ChatEvents()
}