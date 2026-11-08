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

    private val json = Json { ignoreUnknownKeys = true }

    fun getRemoteConfigValues(templates: String, categories: String, config: String): RemoteConfigValues {
        return remoteConfigValueMapper.mapToDomainModel(
            RemoteConfigValuesDto(
                json.decodeFromString<TemplatesDto>(templates).templatesDto,
                json.decodeFromString<CategoriesDto>(categories).categoriesDto,
            )
        ).copy(config = getConfigParsedOrNull(config))
    }

    fun getConfigParsed(config: String): Config {
        return configMapper.mapToDomainModel(json.decodeFromString<ConfigDto>(config))
    }

    /** A missing or malformed config must not stop the templates from loading. */
    private fun getConfigParsedOrNull(config: String): Config? {
        if (config.isBlank()) return null
        return try {
            getConfigParsed(config)
        } catch (e: SerializationException) {
            null
        }
    }

}
