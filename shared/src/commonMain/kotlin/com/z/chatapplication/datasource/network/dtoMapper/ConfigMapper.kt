package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.ConfigDto
import com.z.chatapplication.domain.model.Config
import com.z.chatapplication.domain.util.DomainMapper


class ConfigMapper : DomainMapper<ConfigDto, Config> {
    override fun mapToDomainModel(model: ConfigDto): Config {
        return Config(
            initialCredits = model.initial_credits,
            creditDeduction = model.credit_deduction,
            apiKey = model.api_key
        )
    }

    override fun mapFromDomainModel(domainModel: Config): ConfigDto {
        return ConfigDto(
            initial_credits = domainModel.initialCredits,
            credit_deduction = domainModel.creditDeduction,
            api_key = domainModel.apiKey
        )
    }
}