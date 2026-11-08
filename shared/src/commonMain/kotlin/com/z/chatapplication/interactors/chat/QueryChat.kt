package com.z.chatapplication.interactors.chat

import com.z.chatapplication.datasource.network.ChatService
import com.z.chatapplication.domain.model.Input
import com.z.chatapplication.domain.model.Message
import com.z.chatapplication.domain.util.CommonFlow
import com.z.chatapplication.domain.util.DataState
import com.z.chatapplication.domain.util.asCommonFlow
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.flow

class QueryChat(
    private val chatService: ChatService
) {
    /**
     * Sends the whole conversation so the model can answer follow-up questions.
     * [model] comes from remote config; when it is missing, [DEFAULT_MODEL] is used.
     */
    fun execute(
        history: List<Message>,
        apiKey: String,
        model: String?
    ): CommonFlow<DataState<Message>> = flow {
        emit(DataState.loading())
        if (apiKey.isBlank()) {
            emit(DataState.error("No API key configured. Set api_key in the remote config."))
            return@flow
        }
        try {
            val input = Input(
                model = model?.takeIf { it.isNotBlank() } ?: DEFAULT_MODEL,
                messages = history,
                maxTokens = MAX_TOKENS
            )
            val reply = chatService.queryChat(input, apiKey).choices.firstOrNull()?.text?.trim()
            if (reply.isNullOrEmpty()) {
                emit(DataState.error("The model returned an empty answer."))
            } else {
                emit(
                    DataState.data(
                        message = null,
                        data = Message(history.size, Message.FROM_ASSISTANT, reply, 0)
                    )
                )
            }
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()

    companion object {
        const val DEFAULT_MODEL = "gpt-4o-mini"
        private const val MAX_TOKENS = 500
    }
}
