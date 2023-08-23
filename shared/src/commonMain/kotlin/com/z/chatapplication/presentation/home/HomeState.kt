package com.z.chatapplication.presentation.home

import com.z.chatapplication.domain.model.Category
import com.z.chatapplication.domain.model.Template

data class HomeState(
    val isLoading: Boolean = false,
    val filteredTemplates: List<Template>,
    val selectedCategory: Category = Category(title = "all"),
    val categories: List<Category>,
    val templates: List<Template>
) {
    constructor() : this(
        isLoading = false,
        filteredTemplates = listOf(),
        selectedCategory = Category(title = "all"),
        templates = listOf(),
        categories = listOf()
    )
}