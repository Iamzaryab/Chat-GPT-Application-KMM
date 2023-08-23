package com.z.chatapplication.datasource.remoteconfig

import com.z.chatapplication.datasource.network.dtoMapper.ConfigMapper
import com.z.chatapplication.datasource.network.dtoMapper.RemoteConfigValuesMapper
import com.z.chatapplication.datasource.network.model.*
import com.z.chatapplication.domain.model.Config
import com.z.chatapplication.domain.model.RemoteConfigValues
import kotlinx.serialization.*
import kotlinx.serialization.json.*

class GetRemoteConfigParsed(
    private val remoteConfigValueMapper: RemoteConfigValuesMapper,
    private val configMapper: ConfigMapper
) {

    fun getRemoteConfigValues(templates: String, categories: String): RemoteConfigValues {
        return remoteConfigValueMapper.mapToDomainModel(
            RemoteConfigValuesDto(
                Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<TemplatesDto>(templates).templatesDto,
                Json {
                    ignoreUnknownKeys = true
                }.decodeFromString<CategoriesDto>(categories).categoriesDto,

                )
        )
    }

    fun getConfigParsed(config: String): Config {
        return configMapper.mapToDomainModel(
            Json {
                ignoreUnknownKeys = true
            }.decodeFromString<ConfigDto>(config)

        )
    }

}