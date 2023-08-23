package com.z.chatapplication.datasource.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ConfigDto(
    @SerialName("initial_credits")
    val initial_credits: Int,
    @SerialName("credit_deduction")
    val credit_deduction: Int,
    @SerialName("api_key")
    val api_key: String
)