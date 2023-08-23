package com.z.chatapplication.domain.model

data class Message(
    val index: Int,
    val from: Int,
    val message: String,
    val createAt: Long,
)