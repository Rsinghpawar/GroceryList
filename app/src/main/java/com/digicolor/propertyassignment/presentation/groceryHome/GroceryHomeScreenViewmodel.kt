package com.digicolor.propertyassignment.presentation.groceryHome

import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.TextFieldValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem
import com.digicolor.propertyassignment.domain.usecases.AddGroceryItemUseCase
import com.digicolor.propertyassignment.domain.usecases.GetCategoriesUseCase
import com.digicolor.propertyassignment.domain.usecases.ObserveGroceryListUseCase
import com.digicolor.propertyassignment.domain.usecases.UpdateGroceryItemUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GroceryHomeScreenViewmodel @Inject constructor(
    private val observeGroceryListUseCase: ObserveGroceryListUseCase,
    private val addGroceryItemUseCase: AddGroceryItemUseCase,
    private val getCategoriesUseCase: GetCategoriesUseCase,
    private val updateGroceryItemUseCase: UpdateGroceryItemUseCase,

    ) : ViewModel() {

    private val _groceryListState = MutableStateFlow(GroceryListState())
    val groceryListState = _groceryListState.asStateFlow()

    private val _groceryItemState = MutableStateFlow(GroceryNewItemState())
    val groceryItemState = _groceryItemState.asStateFlow()

    private var currentEditingItem: GroceryItem? = null

    init {
        getCategories()
        observeGroceryList()
    }

    private fun getCategories() {
        viewModelScope.launch {
            getCategoriesUseCase().collect { categories ->
                _groceryItemState.update {
                    it.copy(
                        categoryList = categories.filter { it.name!= DefaultCategories.All.name },
                    )
                }
                _groceryListState.update {
                    it.copy(
                        categoryFilterList = categories
                    )
                }
            }
        }
    }

    private fun observeGroceryList() {
        clearAddState()
        viewModelScope.launch {
            observeGroceryListUseCase().collect { groceryItemList ->
                _groceryListState.update { current ->
                    val displayList = filterAndSort(
                        groceryItemList,
                        categoryFilter = current.selectedCatFilter,
                        sortBy = current.sortBy
                    )
                    current.copy(
                        groceryList = groceryItemList,
                        displayList = displayList
                    )
                }
            }
        }
    }

    fun onAction(uiAction: UiAction) {
        when (uiAction) {
            is UiAction.OnDelete -> {
                viewModelScope.launch {
                    updateGroceryItemUseCase.deleteItem(uiAction.id)
                }
                clearAddState()
            }

            is UiAction.OnAddNewItem -> {
                if (_groceryItemState.value.isAddButtonEnabled) {
                    viewModelScope.launch {
                        if (_groceryItemState.value.isEditing) {
                            currentEditingItem?.let { item ->
                                updateGroceryItemUseCase.updateItem(
                                    grocId = item.id,
                                    name = _groceryItemState.value.textFieldValue.text,
                                    categoryId = _groceryItemState.value.selectedCategory?.name
                                )
                            }

                        } else {
                            addGroceryItemUseCase.invoke(
                                _groceryItemState.value.textFieldValue.text,
                                _groceryItemState.value.selectedCategory
                            )
                        }

                        clearAddState()
                    }

                }
            }

            is UiAction.OnCategoryChange -> {
                val selectedCat = _groceryItemState.value.selectedCategory
                val newCat = _groceryItemState.value.categoryList.find { it.name == uiAction.catID }
                if (selectedCat?.name == newCat?.name) {
                    _groceryItemState.update {
                        it.copy(
                            selectedCategory = null
                        )
                    }
                } else {
                    _groceryItemState.update { it ->
                        it.copy(
                            selectedCategory = it.categoryList.find { it.name == uiAction.catID }
                        )
                    }
                }
            }

            is UiAction.OnInput -> {
                _groceryItemState.update {
                    it.copy(
                        textFieldValue = uiAction.input
                    )
                }
            }

            is UiAction.OnGroceryCheck -> {
                viewModelScope.launch {
                    val grocItem = uiAction.groceryItem
                    updateGroceryItemUseCase.toggleCompletion(grocItem.id, !grocItem.isCompleted)
                    clearAddState()
                }
            }

            is UiAction.OnEditIntent -> {
                currentEditingItem = uiAction.groceryItem
                _groceryItemState.update {
                    it.copy(
                        textFieldValue = TextFieldValue(
                            currentEditingItem?.name.orEmpty(),
                            selection = TextRange(currentEditingItem?.name.orEmpty().length)
                        ),
                        selectedCategory = currentEditingItem?.category,
                        isEditing = true
                    )
                }
            }

            UiAction.OnResetEditingState -> {
                currentEditingItem = null
                _groceryItemState.update {
                    it.copy(
                        isEditing = false
                    )
                }
            }

            is UiAction.OnSort -> {
                clearAddState()
                _groceryListState.update { current ->
                    current.copy(
                        sortBy = uiAction.sortBy,
                        displayList = sortList(
                            current.displayList,
                            uiAction.sortBy
                        )
                    )
                }
            }

            is UiAction.OnCatFilter -> {
                clearAddState()
                _groceryListState.update { current ->
                    current.copy(
                        selectedCatFilter = uiAction.selectedCat,
                        displayList = filterAndSort(
                            current.groceryList,
                            uiAction.selectedCat,
                            current.sortBy
                        )
                    )
                }
            }
        }
    }

    private fun sortList(
        list: List<GroceryItem>,
        sortBy: SortBy
    ): List<GroceryItem> {
        return when (sortBy) {
            SortBy.Category ->
                list.sortedBy { it.category?.name }

            SortBy.DateAdded ->
                list.sortedByDescending { it.dateAdded }

            SortBy.Completed ->
                list.sortedWith(
                    compareBy<GroceryItem> { it.isCompleted }
                        .thenByDescending { it.dateAdded }
                )

            SortBy.Alphabetically -> {
                list.sortedBy { it.name }
            }
        }
    }

    private fun filterAndSort(
        list: List<GroceryItem>,
        categoryFilter: GroceryCategory,
        sortBy: SortBy
    ): List<GroceryItem> {
        // First filter
        val filtered = if (categoryFilter.name == DefaultCategories.All.name) {
            list
        } else {
            list.filter { it.category?.name == categoryFilter.name }
        }

        // Then sort
        return sortList(filtered, sortBy)
    }


    private fun clearAddState() {
        currentEditingItem = null
        _groceryItemState.update {
            it.copy(
                textFieldValue = TextFieldValue(),
                selectedCategory = null,
                isEditing = false
            )
        }
    }


}
