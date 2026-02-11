package com.digicolor.propertyassignment.domain.usecases

import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetCategoriesUseCase @Inject constructor(
    private val repo: GroceryRepo
) {
    operator fun invoke() : Flow<List<GroceryCategory>> = repo.getDefaultCategories()
}