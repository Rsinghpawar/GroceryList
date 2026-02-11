package com.digicolor.propertyassignment.domain


data class GroceryItem(
    val id : Int,
    val name : String,
    val category : GroceryCategory?,
    val dateAdded : Long,
    val isCompleted : Boolean,
)

data class GroceryCategory(
    val name : String,
    val icon : String,
    val textColorHex : String,
    val bgColorHex : String
)