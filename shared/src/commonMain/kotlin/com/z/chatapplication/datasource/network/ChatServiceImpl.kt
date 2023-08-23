package com.z.chatapplication.datasource.network

import com.z.chatapplication.datasource.network.dtoMapper.ChatMapper
import com.z.chatapplication.datasource.network.dtoMapper.InputMapper
import com.z.chatapplication.datasource.network.model.ChatDto
import com.z.chatapplication.domain.model.Chat
import com.z.chatapplication.domain.model.Input
import io.ktor.client.*
import io.ktor.client.call.*
import io.ktor.client.request.*
import io.ktor.http.*

class ChatServiceImpl(
    private val httpClient: HttpClient,
    private val inputMapper: InputMapper,
    private val chatMapper: ChatMapper
) : ChatService {
    override suspend fun queryChat(input: Input, apiKey: String): Chat {
        val chatDto: ChatDto = httpClient.post("https://api.openai.com/v1/completions") {
            contentType(ContentType.Application.Json)
            headers {
                append(
                    HttpHeaders.Authorization,
                    "Bearer $apiKey"
                )
            }
            setBody(inputMapper.mapFromDomainModel(input))
        }.body()
        return chatMapper.mapToDomainModel(chatDto)

//        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

    }
}
