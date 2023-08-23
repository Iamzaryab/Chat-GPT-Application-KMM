package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName

data class RemoteConfigValuesDto(
    @SerialName("templates")
    val templates: List<TemplateDto>,
    @SerialName("categories")
    val categories: List<CategoryDto>
)