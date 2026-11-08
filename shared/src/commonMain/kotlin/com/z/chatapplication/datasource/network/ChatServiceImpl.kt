package com.z.chatapplication.datasource.network

import com.z.chatapplication.datasource.network.dtoMapper.ChatMapper
import com.z.chatapplication.datasource.network.dtoMapper.InputMapper
import com.z.chatapplication.datasource.network.model.ChatDto
import com.z.chatapplication.datasource.network.model.OpenAiErrorDto
import com.z.chatapplication.domain.model.Chat
import com.z.chatapplication.domain.model.Input
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import kotlinx.serialization.json.Json

class ChatServiceImpl(
    private val httpClient: HttpClient,
    private val inputMapper: InputMapper,
    private val chatMapper: ChatMapper
) : ChatService {
    override suspend fun queryChat(input: Input, apiKey: String): Chat {
        val response = httpClient.post(CHAT_COMPLETIONS_URL) {
            contentType(ContentType.Application.Json)
            bearerAuth(apiKey)
            setBody(inputMapper.mapFromDomainModel(input))
        }
        if (!response.status.isSuccess()) {
            throw ChatServiceException(errorMessage(response))
        }
        return chatMapper.mapToDomainModel(response.body<ChatDto>())
    }

    /** Uses OpenAI's own error text when the body has one, so a bad key or model is easy to spot. */
    private suspend fun errorMessage(response: HttpResponse): String {
        // OpenAI sometimes labels the JSON error as text/plain, so decode the raw text ourselves.
        val apiMessage = try {
            errorJson.decodeFromString<OpenAiErrorDto>(response.bodyAsText()).error.message
        } catch (e: Exception) {
            null
        }
        return apiMessage ?: "Request failed with HTTP ${response.status.value}"
    }

    companion object {
        private val errorJson = Json { ignoreUnknownKeys = true }
        private const val CHAT_COMPLETIONS_URL = "https://api.openai.com/v1/chat/completions"
    }
}

class ChatServiceException(message: String) : Exception(message)
