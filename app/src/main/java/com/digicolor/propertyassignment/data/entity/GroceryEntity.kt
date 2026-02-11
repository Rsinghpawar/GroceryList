package com.digicolor.propertyassignment.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("groceries")

data class GroceryEntity(
    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    val name : String,
    val categoryId : String?,
    val completed : Boolean,
    val timeStamp : Long = System.currentTimeMillis()
)

@Entity("categories") // created separate entity so that categories can be scaled, more can be added
data class CategoryEntity(
    @PrimaryKey
    val name : String,
    val icon : String,
    val textColorHex : String,
    val bgColorHex : String
)