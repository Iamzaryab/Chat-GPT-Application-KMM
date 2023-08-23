package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChatDto(

    @SerialName("id")
    val id: String,

    @SerialName("object")
    val obj: String,

    @SerialName("created")
    val created: Long,

    @SerialName("model")
    val model: String,

    @SerialName("choices")
    val choices: List<ChoiceDto>,

    @SerialName("usage")
    val usage: UsageDto

)