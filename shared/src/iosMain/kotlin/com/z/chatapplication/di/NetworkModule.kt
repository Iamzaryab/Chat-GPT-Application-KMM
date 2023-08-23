package com.z.chatapplication.di

import com.z.chatapplication.datasource.network.ChatService
import com.z.chatapplication.datasource.network.ChatServiceImpl
import com.z.chatapplication.datasource.network.KtorClientFactory
import com.z.chatapplication.datasource.network.dtoMapper.ChatMapper
import com.z.chatapplication.datasource.network.dtoMapper.ChoiceMapper
import com.z.chatapplication.datasource.network.dtoMapper.InputMapper
import com.z.chatapplication.datasource.network.dtoMapper.UsageMapper

class NetworkModule {

    val chatService: ChatService by lazy {
        ChatServiceImpl(
            httpClient = KtorClientFactory().build(),
            inputMapper = InputMapper(),
            chatMapper = ChatMapper(
                usageMapper = UsageMapper(),
                choiceMapper = ChoiceMapper()
            )
        )
    }
}