package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TemplatesDto(
    @SerialName("templates")
    val templatesDto: List<TemplateDto>,
)
