package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChoiceDto(

    @SerialName("text")
    val text: String,
    @SerialName("index")
    val index: Int,
    @SerialName("finish_reason")
    val finish_reason: String

)