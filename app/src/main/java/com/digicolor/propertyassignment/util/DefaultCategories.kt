import com.digicolor.propertyassignment.data.entity.CategoryEntity

object DefaultCategories {
    val list = listOf<CategoryEntity>(
        CategoryEntity(
            name = "Vegetables",
            icon = "🥕", // Updated from 🍆 (comment) to match your icon style
            textColorHex = "#FFFFFF",
            bgColorHex = "#AAF683"
        ),
        CategoryEntity(
            name = "Fruits",
            icon = "🍎",
            textColorHex = "#5FC18E",
            bgColorHex = "#FFD97D"
        ),
        CategoryEntity(
            name = "Dairy",
            icon = "🥛",
            textColorHex = "#FFC04C",
            bgColorHex = "#FF9B85"
        ),
        CategoryEntity(
            name = "Meat",
            icon = "🥩",
            textColorHex = "#C62D31",
            bgColorHex = "#E2ADF2"
        ),
        CategoryEntity(
            name = "Bakery",
            icon = "🍞",
            textColorHex = "#C5758B",
            bgColorHex = "#A0CED9"
        )
    )
    val NoCategory = CategoryEntity(
        name = "None",
        icon = "❔",
        textColorHex = "#64748B",
        bgColorHex = "#F1F5F9"
    )
}