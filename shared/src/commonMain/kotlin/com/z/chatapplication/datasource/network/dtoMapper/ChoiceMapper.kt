package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.ChoiceDto
import com.z.chatapplication.domain.model.Choice
import com.z.chatapplication.domain.util.DomainMapper


class ChoiceMapper : DomainMapper<ChoiceDto, Choice> {
    override fun mapToDomainModel(model: ChoiceDto): Choice {
        return Choice(
            text = model.text,
            index = model.index,
            finishReason = model.finish_reason
        )
    }

    override fun mapFromDomainModel(domainModel: Choice): ChoiceDto {
        return ChoiceDto(
            text = domainModel.text,
            index = domainModel.index,
            finish_reason = domainModel.finishReason
        )
    }
    fun toDomainList(initial: List<ChoiceDto>): List<Choice> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Choice>): List<ChoiceDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}