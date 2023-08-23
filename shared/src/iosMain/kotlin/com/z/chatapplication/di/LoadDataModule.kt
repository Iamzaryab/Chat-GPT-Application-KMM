package com.z.chatapplication.di

import com.z.chatapplication.datasource.remoteconfig.GetRemoteConfigParsed
import com.z.chatapplication.interactors.home.LoadData

class LoadDataModule(
    private val getRemoteConfigParsed: GetRemoteConfigParsed
) {
    val loadData: LoadData by lazy {
        LoadData(
            getRemoteConfigParsed
        )
    }
}