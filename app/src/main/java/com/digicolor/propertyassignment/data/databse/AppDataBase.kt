package com.digicolor.propertyassignment.data.databse

import androidx.room.Database
import androidx.room.RoomDatabase
import com.digicolor.propertyassignment.data.dao.CategoriesDao
import com.digicolor.propertyassignment.data.dao.GroceryDao
import com.digicolor.propertyassignment.data.entity.CategoryEntity
import com.digicolor.propertyassignment.data.entity.GroceryEntity

@Database(
    entities = [GroceryEntity::class, CategoryEntity::class],
    version = 1
)
abstract class AppDataBase : RoomDatabase() {
    abstract fun getCategoryDao() : CategoriesDao

    abstract fun groceryDao(): GroceryDao
}