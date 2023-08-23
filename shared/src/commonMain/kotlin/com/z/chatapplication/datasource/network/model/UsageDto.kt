package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UsageDto(
    @SerialName("prompt_tokens")
    val prompt_tokens: Int,
    @SerialName("completion_tokens")
    val completion_tokens: Int,
    @SerialName("total_tokens")
    val total_tokens: Int
)