package com.z.chatapplication.domain.model


data class Chat(
    val id: String,
    val obj: String,
    val created: Long,
    val model: String,
    val choices: List<Choice>,
    val usage: Usage

)