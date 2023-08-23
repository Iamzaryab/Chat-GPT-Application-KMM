package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class TemplateDto(
    @SerialName("title")
    val title: String,
    @SerialName("description")
    val description: String,
    @SerialName("category")
    val category: String,
    @SerialName("question")
    val question: String


)