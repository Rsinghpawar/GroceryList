package com.digicolor.propertyassignment.presentation.groceryHome

import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem

data class GroceryNewItemState(
    val title: String = "",
    val selectedCategory: GroceryCategory?=null,
    val isEditing : Boolean = false,
    val categoryList: List<GroceryCategory> = emptyList()
) {
    val isAddButtonEnabled: Boolean
        get() = title.isNotEmpty()
}

data class GroceryListState(
    val groceryList: List<GroceryItem> = emptyList()
)


sealed interface UiAction {
    object OnAddNewItem : UiAction

    object OnResetEditingState : UiAction

    data class OnDelete(val id: Int) : UiAction

    data class OnEditItem(val id: Int) : UiAction

    data class OnEditIntent(val groceryItem: GroceryItem) : UiAction

    data class OnMarkCompleted(val id: Int) : UiAction
    data class OnInput(val input: String) : UiAction
    data class OnCategoryChange(val catID: String) : UiAction
    data class OnGroceryCheck(val groceryItem: GroceryItem) : UiAction

}