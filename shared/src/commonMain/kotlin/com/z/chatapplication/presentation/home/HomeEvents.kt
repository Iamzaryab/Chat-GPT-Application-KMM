package com.z.chatapplication.presentation.home

import com.z.chatapplication.domain.model.Category

sealed class HomeEvents {
    data class OnSelectedCategory(val category: Category) : HomeEvents()

    data class LoadData(val templates: String, val categories: String, val config: String) : HomeEvents()
}