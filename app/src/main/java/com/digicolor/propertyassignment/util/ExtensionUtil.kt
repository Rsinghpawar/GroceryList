package com.digicolor.propertyassignment.util

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb

fun Color.toHexString(): String {
    return String.format("#%08X", this.toArgb())
}

fun String.toComposeColor(): Color {
    return Color(android.graphics.Color.parseColor(this))
}