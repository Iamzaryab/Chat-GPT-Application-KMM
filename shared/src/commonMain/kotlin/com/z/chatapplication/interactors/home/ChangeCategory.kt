package com.z.chatapplication.interactors.home

import com.codingwithmitch.food2forkkmm.domain.util.CommonFlow
import com.codingwithmitch.food2forkkmm.domain.util.asCommonFlow
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
                    data = if(category.title == "all") templates else templates.filter { s -> s.category == category.title })
            )
        } catch (e: Exception) {
            emit(DataState.error(e.message ?: "Unknown Error"))
        }
    }.asCommonFlow()
}