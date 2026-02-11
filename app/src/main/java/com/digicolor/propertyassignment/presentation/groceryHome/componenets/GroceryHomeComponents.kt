package com.digicolor.propertyassignment.presentation.groceryHome.componenets

import DefaultCategories
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.digicolor.propertyassignment.R
import com.digicolor.propertyassignment.domain.GroceryCategory
import com.digicolor.propertyassignment.domain.GroceryItem
import com.digicolor.propertyassignment.presentation.ui.theme.CategoryLabel
import com.digicolor.propertyassignment.presentation.ui.theme.EmptyStateText
import com.digicolor.propertyassignment.presentation.ui.theme.LightPrimaryGradient
import com.digicolor.propertyassignment.presentation.ui.theme.PropertyAssignmentTheme
import com.digicolor.propertyassignment.presentation.ui.theme.SectionHeader
import com.digicolor.propertyassignment.util.CircleCheckbox
import com.digicolor.propertyassignment.util.toComposeColor


@Composable
fun EmptyGroceryList(modifier: Modifier = Modifier) {
    Column(
        modifier
            .fillMaxWidth()
            .padding(top = 22.dp, bottom = 18.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            modifier = Modifier.size(56.dp),
            tint = Color.Gray,
            painter = painterResource(R.drawable.ic_shopping_cart),
            contentDescription = null,
        )
        Spacer(Modifier.height(12.dp))
        EmptyStateText(
            stringResource(R.string.your_grocery_list_is_empty)
        )
        Spacer(Modifier.height(12.dp))
        Text(
            stringResource(R.string.add_items_above_to_get_started),
            color = Color.Gray

        )
    }
}


@Composable
fun CircularImage(size: Dp = 86.dp) {
    Box(
        modifier = Modifier
            .background(
                shape = RoundedCornerShape(percent = 50), brush = LightPrimaryGradient
            )
            .size(size), contentAlignment = Alignment.Center
    ) {
        Icon(
            modifier = Modifier.size(46.dp),
            tint = Color.White,
            painter = painterResource(R.drawable.ic_card_filled),
            contentDescription = null,
        )
    }
}

@Composable
fun CategoryItem(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    category: GroceryCategory,
    shape: Shape = RoundedCornerShape(12.dp),
    showText: Boolean = true,
    onTap: (() -> Unit)?
) {
    Row(
        modifier = modifier
            .background(
                shape = shape,
                color = if (isSelected) Color.Blue else category.bgColorHex.toComposeColor()
            )
            .padding(vertical = 12.dp, horizontal = 6.dp)
            .clickable {
                onTap?.invoke()
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = category.icon)
            if (showText) {
                Spacer(Modifier.height(10.dp))
                CategoryLabel(
                    category.name,
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                    textColor = category.textColorHex.toComposeColor()
                )
            }

        }
    }
}


@Composable
fun CategoryItemSmall(
    modifier: Modifier = Modifier,
    isSelected: Boolean,
    category: GroceryCategory,
    onTap: (() -> Unit)?
) {
    Row(
        modifier = modifier
            .background(
                shape = RoundedCornerShape(50),
                color = if (isSelected) Color.Blue else category.bgColorHex.toComposeColor()
            )
            .padding(vertical = 12.dp, horizontal = 6.dp)
            .clickable {
                onTap?.invoke()
            },
        horizontalArrangement = Arrangement.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = category.icon)
            CategoryLabel(
                category.name,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                textColor = category.textColorHex.toComposeColor()
            )

        }
    }
}


@Composable
fun GroceryTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = {
            Text(
                text = placeholder,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.5f)
            )
        },
        modifier = modifier.fillMaxWidth(),
        shape = RoundedCornerShape(12.dp),
        colors = OutlinedTextFieldDefaults.colors(
            unfocusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            focusedContainerColor = MaterialTheme.colorScheme.surfaceVariant.copy(alpha = 0.3f),
            unfocusedBorderColor = Color.Transparent,
            focusedBorderColor = MaterialTheme.colorScheme.primary,
        ),
        singleLine = true,
        textStyle = MaterialTheme.typography.bodyLarge
    )
}


@Composable
fun GroceryShoppingListItem(
    modifier: Modifier = Modifier,
    groceryItem: GroceryItem,
    onChecked: () -> Unit,
    onOptionsClick: () -> Unit
) {

    Card(
        modifier
            .fillMaxWidth()
            .padding(horizontal = 12.dp),
        shape = RoundedCornerShape(20),
        elevation = CardDefaults.cardElevation(
            12.dp
        ),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.surface
        )

    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            CircleCheckbox(
                checked = groceryItem.isCompleted,
                onChecked = onChecked
            )
            groceryItem.category.let { category ->
                Box(
                    modifier = Modifier
                        .size(40.dp)
                        .clip(
                            RoundedCornerShape(
                                50
                            )
                        )
                        .background(
                            category?.bgColorHex?.toComposeColor()
                                ?: DefaultCategories.NoCategory.bgColorHex.toComposeColor()
                        ),
                    contentAlignment = Alignment.Center
                ) {
                    Text(category?.icon ?: DefaultCategories.NoCategory.icon)
                }
            }

            Column() {
                SectionHeader(
                    groceryItem.name
                )
                groceryItem.category?.name?.let {
                    CategoryLabel(
                        it,
                        fonSize = 10.sp,
                        textColor = MaterialTheme.colorScheme.tertiary
                    )
                }
            }
            Spacer(Modifier.weight(1f))
            IconButton(
                onClick = onOptionsClick,
                modifier = Modifier.size(32.dp)
            ) {
                Icon(
                    imageVector = Icons.Default.MoreVert,
                    contentDescription = "More options",
                    tint = MaterialTheme.colorScheme.onSurfaceVariant
                )
            }


        }
    }

}


@Preview
@Composable
private fun CategoryListItemPreview() {
    PropertyAssignmentTheme {
        Column(Modifier.fillMaxSize()) {
            FlowRow(
                Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                repeat(5) {
                    CategoryItem(
                        Modifier.weight(1f),
                        isSelected = if (it == 1) true else false,
                        category = GroceryCategory(
                            name = "Milk",
                            icon = "🥛",
                            textColorHex = "#FFFFFF",
                            bgColorHex = "#121212"
                        ),
                        onTap = {},
                    )
                }
            }

            GroceryShoppingListItem(
                groceryItem = GroceryItem(
                    name = "Organic Milk",
                    category = GroceryCategory(
                        name = "Diary",
                        icon = "🥕",
                        textColorHex = "#5FC18E",
                        bgColorHex = "#E9F9EE"
                    ),
                    dateAdded = 10L,
                    isCompleted = false,
                    id = 1
                ),
                onChecked = {},
                onOptionsClick = {}
            )
        }
    }


}