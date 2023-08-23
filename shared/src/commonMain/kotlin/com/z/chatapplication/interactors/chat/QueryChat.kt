package com.z.chatapplication.interactors.chat

import com.codingwithmitch.food2forkkmm.domain.util.CommonFlow
import com.codingwithmitch.food2forkkmm.domain.util.asCommonFlow
import com.z.chatapplication.datasource.network.ChatService
import com.z.chatapplication.domain.model.Input
import com.z.chatapplication.domain.model.Message
import com.z.chatapplication.domain.util.DataState
import kotlinx.coroutines.flow.flow

class QueryChat(
    private val chatService: ChatService
) {
    fun execute(
        input: Input,
        apiKey: String
    ): CommonFlow<DataState<Message>> = flow {
        emit(DataState.loading())
        try {
            emit(

                DataState.data(
                    message = null,
                    data = Message(0,1, chatService.queryChat(input, apiKey).choices[0].text.trim(),0)
                )
            )


        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()
}