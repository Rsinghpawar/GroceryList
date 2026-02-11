package com.digicolor.propertyassignment.presentation.groceryHome

import com.digicolor.propertyassignment.data.mappers.toDomain
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem

data class GroceryNewItemState(
    val title: String = "",
    val selectedCategory: GroceryCategory? = null,
    val isEditing: Boolean = false,
    val categoryList: List<GroceryCategory> = emptyList(),
    val selectedCatFilter : GroceryCategory?=null
) {
    val isAddButtonEnabled: Boolean
        get() = title.isNotEmpty()
}

data class GroceryListState(
    val groceryList: List<GroceryItem> = emptyList(),
    val sortBy: SortBy = SortBy.Completed,
    val sortOptions: List<SortBy> = SortBy.entries.toList(),
    val displayList: List<GroceryItem> = emptyList(), //sorted and filtered
    val categoryFilterList: List<GroceryCategory> = emptyList(),
    val selectedCatFilter : GroceryCategory = DefaultCategories.All.toDomain() // anti-pattern, need to be changed

    )

enum class SortBy {
    Completed,
    Category,
    DateAdded,
    Alphabetically,
}


sealed interface UiAction {
    object OnAddNewItem : UiAction

    object OnResetEditingState : UiAction

    data class OnDelete(val id: Int) : UiAction

    data class OnEditIntent(val groceryItem: GroceryItem) : UiAction
    data class OnInput(val input: String) : UiAction
    data class OnCategoryChange(val catID: String) : UiAction
    data class OnGroceryCheck(val groceryItem: GroceryItem) : UiAction
    data class OnSort(val sortBy: SortBy) : UiAction

    data class OnCatFilter(val selectedCat: GroceryCategory) : UiAction

}