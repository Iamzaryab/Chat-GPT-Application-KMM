package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChoiceDto(
    @SerialName("index")
    val index: Int,
    @SerialName("message")
    val message: ChatMessageDto,
    @SerialName("finish_reason")
    val finish_reason: String? = null
)
