package com.z.chatapplication.datasource.network.dtoMapper

import com.z.chatapplication.datasource.network.model.CategoryDto
import com.z.chatapplication.domain.model.Category
import com.z.chatapplication.domain.util.DomainMapper

class CategoryMapper : DomainMapper<CategoryDto, Category> {
    override fun mapToDomainModel(model: CategoryDto): Category {
        return Category(
            title = model.title
        )
    }

    override fun mapFromDomainModel(domainModel: Category): CategoryDto {
        return CategoryDto(
            title = domainModel.title
        )
    }
    fun toDomainList(initial: List<CategoryDto>): List<Category> {
        return initial.map { mapToDomainModel(it) }
    }

    fun fromDomainList(initial: List<Category>): List<CategoryDto> {
        return initial.map { mapFromDomainModel(it) }
    }
}