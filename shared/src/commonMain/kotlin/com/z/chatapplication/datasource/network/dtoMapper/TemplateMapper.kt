package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.TemplateDto
import com.z.chatapplication.domain.model.Template
import com.z.chatapplication.domain.util.DomainMapper


class TemplateMapper : DomainMapper<TemplateDto, Template> {
    override fun mapToDomainModel(model: TemplateDto): Template {
        return Template(
            title = model.title,
            category = model.category,
            description = model.description,
            question = model.question
        )
    }

    override fun mapFromDomainModel(domainModel: Template): TemplateDto {
        return TemplateDto(
            title = domainModel.title,
            category = domainModel.category,
            description = domainModel.description,
            question = domainModel.question
        )
    }

    fun toDomainList(initial: List<TemplateDto>): List<Template> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Template>): List<TemplateDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}