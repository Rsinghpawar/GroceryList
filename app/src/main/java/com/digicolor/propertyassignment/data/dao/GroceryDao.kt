package com.digicolor.propertyassignment.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.digicolor.propertyassignment.data.entity.GroceryEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface GroceryDao {

    @Query("select * from groceries")
    fun getGroceryList() : Flow<List<GroceryEntity>>

    @Insert
    fun addGrocery(groceryEntity: GroceryEntity)

    @Query("delete from groceries where id = :id")
    fun deleteGrocery(id : Int)

    @Query("""
    UPDATE groceries 
    SET completed = :checked 
    WHERE id = :id
""")
    suspend fun updateChecked(id: Int, checked: Boolean)

    @Query("""
    UPDATE groceries 
    SET name = :name ,categoryId = :categoryId
    WHERE id = :id
""")
    suspend fun updateGroceryDetails(id: Int, name : String, categoryId : String?)



}