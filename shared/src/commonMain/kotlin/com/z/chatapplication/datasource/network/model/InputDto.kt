package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
@kotlinx.serialization.Serializable
data class InputDto(
    @SerialName("model")
    val model: String,
    @SerialName("prompt")
    val prompt: String,
    @SerialName("temperature")
    val temperature: Int,
    @SerialName("max_tokens")
    val max_tokens: Int
)
