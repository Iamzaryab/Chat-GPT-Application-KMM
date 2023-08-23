package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.ChatDto
import com.z.chatapplication.domain.model.Chat
import com.z.chatapplication.domain.util.DomainMapper


class ChatMapper(
    private val usageMapper: UsageMapper,
    private val choiceMapper: ChoiceMapper,
) : DomainMapper<ChatDto, Chat> {
    override fun mapToDomainModel(model: ChatDto): Chat {
        return Chat(
            id = model.id,
            obj = model.obj,
            created = model.created,
            model = model.model,
            choices = choiceMapper.toDomainList(model.choices),
            usage = usageMapper.mapToDomainModel(model.usage)
        )
    }

    override fun mapFromDomainModel(domainModel: Chat): ChatDto {
        return ChatDto(
            id = domainModel.id,
            obj = domainModel.obj,
            created = domainModel.created,
            model = domainModel.model,
            choices = choiceMapper.fromDomainList(domainModel.choices),
            usage = usageMapper.mapFromDomainModel(domainModel.usage)
        )

    }

}