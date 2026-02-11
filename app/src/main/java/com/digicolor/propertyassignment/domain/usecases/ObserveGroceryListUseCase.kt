package com.digicolor.propertyassignment.domain.usecases

import com.digicolor.propertyassignment.domain.GroceryItem
import com.digicolor.propertyassignment.domain.GroceryRepo
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class ObserveGroceryListUseCase @Inject constructor(
    private val repo: GroceryRepo
) {
    operator fun invoke(): Flow<List<GroceryItem>> = repo.observeGroceryList()
}