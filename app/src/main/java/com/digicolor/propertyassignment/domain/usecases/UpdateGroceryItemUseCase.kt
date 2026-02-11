package com.digicolor.propertyassignment.domain.usecases

import com.digicolor.propertyassignment.domain.GroceryRepo
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UpdateGroceryItemUseCase @Inject constructor(
    private val groceryRepo: GroceryRepo
) {
    suspend fun toggleCompletion(grocId: Int, checked: Boolean) {
        withContext(Dispatchers.IO) {
            groceryRepo.toggleCompletion(grocId, checked)
        }
    }

    suspend fun updateItem(grocId: Int, name: String, categoryId: String?) {
        withContext(Dispatchers.IO) {
            groceryRepo.editGrocery(
                grocId,
                name,
                categoryId
            )
        }
    }

    suspend fun deleteItem(id : Int){
        withContext(Dispatchers.IO) {
            groceryRepo.deleteGroceryItem(
                id
            )
        }
    }

}