package com.digicolor.propertyassignment.presentation.ui.theme

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

// Light theme
val Purple40 = Color(0xFF9C27B0)
val PurpleGrey40 = Color(0xFF6B6480)
val Pink40 = Color(0xFFDE3D7F)

// Dark theme
val Purple80 = Color(0xFFC7B6FF)
val PurpleGrey80 = Color(0xFFCDC5E0)
val Pink80 = Color(0xFFF1B7C9)

// Backgrounds
val PrimaryBackground = Color(0xFFF4F3F8)
val DarkPrimaryBackground = Color(0xFF121212)



val LightPrimaryGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFFAA51D6), // purple
        Color(0xFF3A74FB)  // blue
    )
)

val DarkPrimaryGradient = Brush.horizontalGradient(
    colors = listOf(
        Color(0xFF7E3DB2),
        Color(0xFF2F5EDB)
    )
)


