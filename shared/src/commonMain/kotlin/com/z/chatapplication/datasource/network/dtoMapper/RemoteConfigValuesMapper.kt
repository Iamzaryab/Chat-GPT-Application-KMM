package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.RemoteConfigValuesDto
import com.z.chatapplication.domain.model.RemoteConfigValues
import com.z.chatapplication.domain.util.DomainMapper


class RemoteConfigValuesMapper(
    private val templateMapper: TemplateMapper,
    private val categoryMapper: CategoryMapper
) : DomainMapper<RemoteConfigValuesDto, RemoteConfigValues> {
    override fun mapToDomainModel(model: RemoteConfigValuesDto): RemoteConfigValues {
        return RemoteConfigValues(
            templates = templateMapper.toDomainList(model.templates),
            categories = categoryMapper.toDomainList(model.categories)
        )
    }

    override fun mapFromDomainModel(domainModel: RemoteConfigValues): RemoteConfigValuesDto {

        return RemoteConfigValuesDto(
            templates = templateMapper.fromDomainList(domainModel.templates),
            categories = categoryMapper.fromDomainList(domainModel.categories)
        )
    }

    fun toDomainList(initial: List<RemoteConfigValuesDto>): List<RemoteConfigValues> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<RemoteConfigValues>): List<RemoteConfigValuesDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}