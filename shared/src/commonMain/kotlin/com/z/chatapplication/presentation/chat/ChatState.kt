package com.z.chatapplication.presentation.chat

import com.z.chatapplication.domain.model.Message

data class ChatState(
    val isLoading: Boolean = false,
    val messages: List<Message>,
    val lastMessage: Message?,
    val btnActive: Boolean,
    val strHint: String,
    val query: String
) {
    constructor() : this(
        isLoading = false,
        messages = listOf(),
        lastMessage = null,
        btnActive = false,
        strHint = "Ask me Anything",
        query = ""
    )
}