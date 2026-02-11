package com.digicolor.propertyassignment.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.digicolor.propertyassignment.data.entity.CategoryEntity
import com.digicolor.propertyassignment.data.entity.GroceryEntity
import com.digicolor.propertyassignment.domain.GroceryItem
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoriesDao {

    @Query("select * from categories")
    fun getAllCategories() : Flow<List<CategoryEntity>>

    @Query("select * from categories where name = :id")
    suspend fun getCategory(id : String) : CategoryEntity?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(categories : List<CategoryEntity>)

}