package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.UsageDto
import com.z.chatapplication.domain.model.Usage
import com.z.chatapplication.domain.util.DomainMapper


class UsageMapper : DomainMapper<UsageDto, Usage> {
    override fun mapToDomainModel(model: UsageDto): Usage {
        return Usage(
            promptTokens = model.prompt_tokens,
            completionTokens = model.completion_tokens,
            totalTokens = model.total_tokens
        )
    }

    override fun mapFromDomainModel(domainModel: Usage): UsageDto {
        return UsageDto(
            prompt_tokens = domainModel.promptTokens,
            completion_tokens = domainModel.completionTokens,
            total_tokens = domainModel.totalTokens
        )
    }
}