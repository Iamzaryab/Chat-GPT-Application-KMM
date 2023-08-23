package com.z.chatapplication.di

import com.z.chatapplication.datasource.network.dtoMapper.CategoryMapper
import com.z.chatapplication.datasource.network.dtoMapper.ConfigMapper
import com.z.chatapplication.datasource.network.dtoMapper.RemoteConfigValuesMapper
import com.z.chatapplication.datasource.network.dtoMapper.TemplateMapper
import com.z.chatapplication.datasource.remoteconfig.GetRemoteConfigParsed

class RemoteConfigModule {
    val getRemoteConfigParsed: GetRemoteConfigParsed by lazy {
        GetRemoteConfigParsed(
            remoteConfigValueMapper = RemoteConfigValuesMapper(
                templateMapper = TemplateMapper(),
                categoryMapper = CategoryMapper(),
            ),
            configMapper = ConfigMapper()
        )
    }

}