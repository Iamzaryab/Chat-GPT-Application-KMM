package com.z.chatapplication.interactors.home

import com.z.chatapplication.domain.util.CommonFlow
import com.z.chatapplication.domain.util.asCommonFlow
import com.z.chatapplication.domain.model.Category
import com.z.chatapplication.domain.model.Template
import com.z.chatapplication.domain.util.DataState
import kotlinx.coroutines.flow.flow

class ChangeCategory{
    fun execute(
        category: Category,
        templates: List<Template>
    ): CommonFlow<DataState<List<Template>>> = flow {
        try {
            emit(DataState.loading())
            emit(
                DataState.data<List<Template>>(
                    message = null,
                    data = if (category.title.equals("all", ignoreCase = true)) templates
                    else templates.filter { it.category.equals(category.title, ignoreCase = true) })
            )
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()
}