package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.ChatMessageDto
import com.z.chatapplication.datasource.network.model.InputDto
import com.z.chatapplication.domain.model.Input
import com.z.chatapplication.domain.model.Message
import com.z.chatapplication.domain.util.DomainMapper


class InputMapper : DomainMapper<InputDto, Input> {
    override fun mapToDomainModel(model: InputDto): Input {
        return Input(
            model = model.model,
            messages = model.messages.mapIndexed { index, dto ->
                Message(
                    index = index,
                    from = if (dto.role == ChatMessageDto.ROLE_USER) Message.FROM_USER else Message.FROM_ASSISTANT,
                    message = dto.content.orEmpty(),
                    createAt = 0
                )
            },
            maxTokens = model.max_completion_tokens
        )
    }

    override fun mapFromDomainModel(domainModel: Input): InputDto {
        return InputDto(
            model = domainModel.model,
            messages = domainModel.messages.map {
                ChatMessageDto(
                    role = if (it.from == Message.FROM_USER) ChatMessageDto.ROLE_USER else ChatMessageDto.ROLE_ASSISTANT,
                    content = it.message
                )
            },
            max_completion_tokens = domainModel.maxTokens
        )
    }
}
