package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/** Error body OpenAI returns with a non-2xx status, e.g. {"error": {"message": "..."}}. */
@Serializable
data class OpenAiErrorDto(
    @SerialName("error")
    val error: Detail
) {
    @Serializable
    data class Detail(
        @SerialName("message")
        val message: String
    )
}
