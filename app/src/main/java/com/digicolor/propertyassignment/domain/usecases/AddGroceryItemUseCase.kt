package com.digicolor.propertyassignment.domain.usecases

import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AddGroceryItemUseCase @Inject constructor(
    private val repo: GroceryRepo
) {
    suspend operator fun invoke(
        title: String,
        category: GroceryCategory?=null
    ) {
        withContext(Dispatchers.IO){
            repo.addGroceryItem(
                title,
                category?.name
            )
        }
    }
}