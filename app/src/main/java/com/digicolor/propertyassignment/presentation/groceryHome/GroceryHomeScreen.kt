package com.digicolor.propertyassignment.presentation.groceryHome

import android.annotation.SuppressLint
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.isImeVisible
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.relocation.BringIntoViewRequester
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.digicolor.propertyassignment.R
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.CategoryItem
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.CircularImage
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.EmptyGroceryList
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.GroceryShoppingListItem
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.GroceryTextField
import com.digicolor.propertyassignment.presentation.ui.theme.ButtonText
import com.digicolor.propertyassignment.presentation.ui.theme.SectionHeader
import com.digicolor.propertyassignment.presentation.ui.theme.SubtitleText
import com.digicolor.propertyassignment.presentation.ui.theme.TitleLarge
import com.digicolor.propertyassignment.presentation.ui.theme.LightPrimaryGradient
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.ActionIcon
import com.digicolor.propertyassignment.presentation.groceryHome.componenets.SwipeableItemWithActions
import com.digicolor.propertyassignment.util.SimpleDropdown
import com.digicolor.propertyassignment.util.toComposeColor
import kotlinx.coroutines.delay

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun GroceryHomeScreen(vm: GroceryHomeScreenViewmodel) {
    val groceryListState by vm.groceryListState.collectAsStateWithLifecycle()
    val groceryItemState by vm.groceryItemState.collectAsStateWithLifecycle()

    GroceryHomeScreenContent(
        groceryListState,
        groceryItemState,
        vm::onAction
    )
}

@Composable
fun GroceryHomeScreenContent(
    groceryListState: GroceryListState,
    newItemState: GroceryNewItemState,
    uiAction: (UiAction) -> Unit,
) {
    Scaffold() { paddingValues ->

        var revealedItemId by remember { mutableStateOf<Int?>(null) }
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
                .padding(paddingValues)
                .imePadding(),
            contentPadding = PaddingValues(
                bottom = 80.dp
            ),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                AppBarSection(
                    modifier = Modifier.padding(start = 32.dp, end = 32.dp, top = 22.dp)
                )
            }

            item {
                AddNewItemSection(
                    newItemState,
                    onValueChange = {
                        uiAction(UiAction.OnInput(it))
                    },
                    onCategoryChange = {
                        uiAction(UiAction.OnCategoryChange(it))
                    },
                    onAddItem = {
                        uiAction(UiAction.OnAddNewItem)
                    },
                    onResetEditingState = {
                        uiAction(UiAction.OnAddNewItem)
                    }
                )
            }
            if (groceryListState.groceryList.isNotEmpty()) {
                item {
                    YourShoppingList(
                        size = groceryListState.groceryList.size,
                        catList = groceryListState.categoryFilterList,
                        selectedCat = groceryListState.selectedCatFilter,
                        sortOptions = groceryListState.sortOptions,
                        sortBy = groceryListState.sortBy,
                        onSort = {
                            uiAction(UiAction.OnSort(sortBy = it))
                        },
                        onCategorySelected = {
                            uiAction(UiAction.OnCatFilter(selectedCat = it))
                        }
                    )
                }
                items(groceryListState.displayList, key = { it.id }) { groceryItem ->
                    SwipeableItemWithActions(
                        modifier = Modifier.animateItem(),
                        isRevealed = groceryItem.id == revealedItemId,
                        actions = {
                            ActionIcon(
                                onClick = {
                                    uiAction(UiAction.OnDelete(groceryItem.id))
                                    revealedItemId = null
                                },
                                backgroundColor = MaterialTheme.colorScheme.surface,
                                icon = Icons.Default.Delete,
                                tint = MaterialTheme.colorScheme.error,
                                modifier = Modifier.fillMaxHeight(),
                            )
                            ActionIcon(
                                onClick = {
                                    uiAction(UiAction.OnEditIntent(groceryItem))
                                    revealedItemId = null
                                },
                                backgroundColor = MaterialTheme.colorScheme.surface,
                                icon = Icons.Default.Edit,
                                tint = MaterialTheme.colorScheme.onSurfaceVariant,
                                modifier = Modifier.fillMaxHeight()
                            )
                        },
                        onExpanded = { revealedItemId = groceryItem.id },
                        onCollapsed = { revealedItemId = null }
                    ) {
                        GroceryShoppingListItem(
                            groceryItem = groceryItem,
                            onChecked = {
                                uiAction(UiAction.OnGroceryCheck(groceryItem))
                                revealedItemId = null
                            },
                            onOptionsClick = {
                                revealedItemId = if (revealedItemId == groceryItem.id) {
                                    null
                                } else {
                                    groceryItem.id
                                }
                            }
                        )
                    }
                }
            } else {
                item {
                    EmptyGroceryList()
                }
            }

        }

    }
}

@Composable
fun CategoryFilter(newItemState: GroceryNewItemState, onCateFilterChange: (String) -> Unit) {
    FlowRow(
        modifier = Modifier
            .fillMaxWidth()
            .padding(start = 14.dp, end = 14.dp, bottom = 12.dp),
        maxLines = 1,
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        newItemState.categoryList.forEachIndexed { _, category ->
            CategoryItem(
                modifier = Modifier.weight(1f),
                isSelected = newItemState.selectedCatFilter?.name == category.name,
                onTap = {
                    onCateFilterChange(category.name)
                },
                category = category
            )
        }
    }
}

@Composable
fun YourShoppingList(
    size: Int,
    catList: List<GroceryCategory>,
    selectedCat: GroceryCategory,
    sortOptions: List<SortBy>,
    sortBy: SortBy,
    onSort: (SortBy) -> Unit,
    onCategorySelected: (GroceryCategory) -> Unit
) {
    var sortExpanded by remember { mutableStateOf(false) }
    var categoryExpanded by remember { mutableStateOf(false) }

    Column(
        Modifier
            .fillMaxWidth()
            .padding(horizontal = 14.dp, vertical = 12.dp),
    ) {
        Row(
            horizontalArrangement = Arrangement.spacedBy(12.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                stringResource(R.string.your_shopping_list),
                fontWeight = FontWeight.Bold,
                color = Color(0xff9CA3AF)
            )
            Spacer(Modifier.weight(1f))
            Text("$size Item(s)", color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)

            SimpleDropdown(
                items = sortOptions,
                selectedItem = sortBy,
                expanded = sortExpanded,
                onExpandedChange = { sortExpanded = it },
                onItemSelected = onSort,
                itemLabel = { it.name },
                icon = Icons.AutoMirrored.Filled.List
            )
        }

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(selectedCat.name, color = selectedCat.bgColorHex.toComposeColor())

            SimpleDropdown(
                items = catList,
                selectedItem = selectedCat,
                expanded = categoryExpanded,
                onExpandedChange = { categoryExpanded = it },
                onItemSelected = onCategorySelected,
                itemLabel = { it.name },
                icon = Icons.Filled.PlayArrow,
                iconTint = selectedCat.bgColorHex.toComposeColor()
            )
        }
    }
}



@OptIn(ExperimentalLayoutApi::class)
@Composable
fun AddNewItemSection(
    newItemState: GroceryNewItemState,
    onValueChange: (String) -> Unit,
    onCategoryChange: (String) -> Unit,
    onAddItem: () -> Unit,
    onResetEditingState: () -> Unit
) {
    val bringIntoViewRequester = remember { BringIntoViewRequester() }

    var isTextFieldFocused by remember { mutableStateOf(false) }
    val isKeyboardOpen = WindowInsets.isImeVisible

    val focusRequester = remember { FocusRequester() }

    LaunchedEffect(isKeyboardOpen, isTextFieldFocused) {
        if (isKeyboardOpen && isTextFieldFocused) {
            delay(100)
            bringIntoViewRequester.bringIntoView()
        }
    }
    LaunchedEffect(newItemState.isEditing) {
        if (newItemState.isEditing) {
            delay(100)
            focusRequester.requestFocus()
        }
    }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(4.dp),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )
    ) {
        Column() {
            AddNewItemGradientCTA(newItemState.isEditing, onReset = onResetEditingState)
            Column(
                modifier = Modifier
                    .padding(vertical = 18.dp, horizontal = 16.dp)
            ) {
                SectionHeader(stringResource(R.string.item_name))
                GroceryTextField(
                    modifier = Modifier
                        .fillMaxWidth()
                        .focusRequester(focusRequester)
                        .onFocusEvent { focusState ->
                            isTextFieldFocused = focusState.isFocused
                        },
                    value = newItemState.title,
                    onValueChange = onValueChange,
                    placeholder = stringResource(R.string.enter_grocery_item),
                )
                Spacer(Modifier.height(24.dp))
                SectionHeader(stringResource(R.string.category))
                Spacer(Modifier.height(12.dp))
                FlowRow(
                    modifier = Modifier
                        .fillMaxWidth(),
                    maxLines = 1,
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    newItemState.categoryList.forEachIndexed { _, category ->
                        CategoryItem(
                            modifier = Modifier.weight(1f),
                            isSelected = newItemState.selectedCategory?.name == category.name,
                            onTap = {
                                onCategoryChange(category.name)
                            },
                            category = category
                        )
                    }
                }
                Spacer(Modifier.height(22.dp))

                CustomAddCTA(
                    newItemState.isAddButtonEnabled,
                    onAddItem,
                    isEditing = newItemState.isEditing,
                    modifier = Modifier.bringIntoViewRequester(bringIntoViewRequester)
                )
            }


        }
    }
}

@Composable
fun CustomAddCTA(
    isEnabled: Boolean,
    onTap: () -> Unit,
    isEditing: Boolean,
    modifier: Modifier = Modifier
) {
    Button(
        onClick = onTap,
        modifier = modifier.fillMaxWidth(),
        enabled = isEnabled,
        shape = RoundedCornerShape(12.dp),
    ) {
        Row(modifier = Modifier.padding(8.dp)) {
            ButtonText("+")
            Spacer(Modifier.width(12.dp))
            ButtonText(if (isEditing) stringResource(R.string.update) else stringResource(R.string.add_item))
        }
    }
}

@Composable
fun AddNewItemGradientCTA(isEditing: Boolean, onReset: () -> Unit) {
    Row(
        Modifier
            .fillMaxWidth()
            .background(
                shape = RoundedCornerShape(
                    12.dp,
                ), brush = LightPrimaryGradient
            )
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        AnimatedContent(
            targetState = isEditing,
            transitionSpec = {
                // Text slides up and fades in, while the old text slides up and fades out
                (slideInVertically { height -> height } + fadeIn())
                    .togetherWith(slideOutVertically { height -> -height } + fadeOut())
            }
        ) { editing ->
            Text(
                text = if (editing) stringResource(R.string.update_item) else stringResource(R.string.add_new_item),
                color = Color.White,
                style = MaterialTheme.typography.titleSmall,
            )
        }
        Spacer(Modifier.weight(1f))
        if (isEditing) {
            Box(
                modifier = Modifier
                    .size(16.dp)
                    .clickable() {
                        onReset()
                    }) {
                Icon(
                    Icons.Filled.Clear,
                    contentDescription = null,
                    tint = Color.White,
                )
            }
        }
    }
}

@Composable
fun AppBarSection(modifier: Modifier = Modifier) {
    Column(modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
        CircularImage()
        Spacer(Modifier.height(12.dp))
        TitleLarge(stringResource(R.string.grocery_list))
        Spacer(Modifier.height(12.dp))
        SubtitleText(stringResource(R.string.add_items_to_your_shopping_list))
    }
}


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Preview
@Composable
private fun GroceryHomeScreenPreview() {
    Scaffold() {
        GroceryHomeScreenContent(
            GroceryListState(
                listOf(
                    GroceryItem(
                        id = 1,
                        name = "Testing",
                        category = null,
                        dateAdded = 1L,
                        isCompleted = false,
                    )
                )
            ),
            GroceryNewItemState(
                categoryList = listOf(
                    GroceryCategory(
                        name = "Vegetables",
                        icon = "\uD83C\uDF46",
                        textColorHex = "#FFFFFF",
                        bgColorHex = "#AAF683"
                    ),
                    GroceryCategory(
                        name = "Fruits",
                        icon = "\uD83C\uDF4E",
                        textColorHex = "#5FC18E",
                        bgColorHex = "#FFD97D"
                    ),
                    GroceryCategory(
                        name = "Dairy",
                        icon = "\uD83E\uDD5B",
                        textColorHex = "#FFC04C",
                        bgColorHex = "#FF9B85"
                    ),
                    GroceryCategory(
                        name = "Meat",
                        icon = "\uD83C\uDF56",
                        textColorHex = "#C62D31",
                        bgColorHex = "#E2ADF2"
                    ),
                    GroceryCategory(
                        name = "Bakery",
                        icon = "\uD83C\uDF5E",
                        textColorHex = "#C5758B",
                        bgColorHex = "#A0CED9"
                    ),
                )
            ),
            uiAction = {}
        )
    }
}

