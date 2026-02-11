package com.digicolor.propertyassignment.data.mappers

import com.digicolor.propertyassignment.data.entity.CategoryEntity
import com.digicolor.propertyassignment.data.entity.GroceryEntity
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem

fun GroceryEntity.toDomain(catMap : Map<String, GroceryCategory>): GroceryItem {
    return GroceryItem(
        id = id,
        name = name,
        category = catMap[this.categoryId],
        dateAdded = timeStamp,
        isCompleted = completed
    )
}

fun CategoryEntity.toDomain() : GroceryCategory{
    return GroceryCategory(
        name = name,
        icon = icon,
        textColorHex = textColorHex,
        bgColorHex = bgColorHex
    )
}