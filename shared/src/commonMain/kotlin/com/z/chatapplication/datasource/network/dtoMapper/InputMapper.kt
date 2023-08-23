package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.InputDto
import com.z.chatapplication.domain.model.Input
import com.z.chatapplication.domain.util.DomainMapper


class InputMapper : DomainMapper<InputDto, Input> {
    override fun mapToDomainModel(model: InputDto): Input {
        return Input(
            model = model.model,
            prompt = model.prompt,
            temperature = model.temperature,
            maxTokens = model.max_tokens
        )
    }

    override fun mapFromDomainModel(domainModel: Input): InputDto {
        return InputDto(
            model = domainModel.model,
            prompt = domainModel.prompt,
            temperature = domainModel.temperature,
            max_tokens = domainModel.maxTokens
        )
    }
}