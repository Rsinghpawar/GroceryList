package com.digicolor.propertyassignment.domain

import kotlinx.coroutines.flow.Flow

interface GroceryRepo {

    fun observeGroceryList() : Flow<List<GroceryItem>>

    fun addGroceryItem(
        title : String,
        categoryId : String?
    )

    fun deleteGroceryItem(id : Int)

    fun getDefaultCategories() : Flow<List<GroceryCategory>>

    suspend fun toggleCompletion(grocId : Int, checked : Boolean)

    suspend fun editGrocery(
        id : Int,
        title : String,
        categoryId : String?
    )
}