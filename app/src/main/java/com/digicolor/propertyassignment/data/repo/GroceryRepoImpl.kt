package com.digicolor.propertyassignment.data.repo

import com.digicolor.propertyassignment.data.dao.CategoriesDao
import com.digicolor.propertyassignment.data.dao.GroceryDao
import com.digicolor.propertyassignment.data.entity.GroceryEntity
import com.digicolor.propertyassignment.data.mappers.toDomain
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem
import com.digicolor.propertyassignment.domain.GroceryRepo
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GroceryRepoImpl @Inject constructor(
    private val groceryDao: GroceryDao,
    private val categoriesDao: CategoriesDao
) : GroceryRepo {
    override fun observeGroceryList(): Flow<List<GroceryItem>> {
        return combine(
            categoriesDao.getAllCategories(),
            groceryDao.getGroceryList()
        ) { catEntities, grocEntities ->

            val catMap: Map<String, GroceryCategory> = catEntities.associate { entity ->
                entity.name to entity.toDomain()
            }

            grocEntities.map { groceryEntity ->
                groceryEntity.toDomain(catMap)
            }

        }

    }

    override fun addGroceryItem(title: String, categoryId: String?) {
        groceryDao.addGrocery(
            GroceryEntity(
                name = title,
                categoryId = categoryId,
                completed = false,
            )
        )
    }

    override fun deleteGroceryItem(id: Int) {
        groceryDao.deleteGrocery(id)
    }

    override fun getDefaultCategories(): Flow<List<GroceryCategory>> {
        return categoriesDao.getAllCategories().map { entities ->
            entities.map {
                it.toDomain()
            }
        }
    }

    override suspend fun toggleCompletion(grocId: Int, checked: Boolean) {
        groceryDao.updateChecked(grocId,checked)
    }

    override suspend fun editGrocery(
        id: Int,
        title: String,
        categoryId: String?
    ) {
        groceryDao.updateGroceryDetails(
            id = id,
            name = title,
            categoryId = categoryId
        )
    }
}