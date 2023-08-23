package com.z.chatapplication.interactors.home

import com.codingwithmitch.food2forkkmm.domain.util.CommonFlow
import com.codingwithmitch.food2forkkmm.domain.util.asCommonFlow
import com.z.chatapplication.datasource.remoteconfig.GetRemoteConfigParsed
import com.z.chatapplication.domain.model.RemoteConfigValues
import com.z.chatapplication.domain.util.DataState
import kotlinx.coroutines.flow.flow

class LoadData(
    private val getRemoteConfigParsed: GetRemoteConfigParsed
) {
    fun execute(
        templates: String,
        categories: String,
        config: String

    ): CommonFlow<DataState<RemoteConfigValues>> = flow {
        emit(DataState.loading())

        try {
            emit(
                DataState.data<RemoteConfigValues>(
                    message = null,
                    data = getRemoteConfigParsed.getRemoteConfigValues(
                        templates = templates,
                        categories = categories
                    )
                )
            )

        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown error"))
        }
    }.asCommonFlow()
}