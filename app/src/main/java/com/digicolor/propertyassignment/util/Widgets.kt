package com.digicolor.propertyassignment.util

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import com.digicolor.propertyassignment.R


@Composable
fun CircleCheckbox(
    checked: Boolean,
    enabled: Boolean = true,
    onChecked: () -> Unit
) {
    val colors = MaterialTheme.colorScheme


    val tint by animateColorAsState(
        targetValue = if (checked)
            colors.primary
        else
            colors.onSurfaceVariant.copy(alpha = 0.6f),
        label = "tint"
    )


    val background by animateColorAsState(
        targetValue = if (checked) Color.White else Color.Transparent,
        label = "background"
    )

    val scale by animateFloatAsState(
        targetValue = if (checked) 1.1f else 1f,
        animationSpec = spring(
            dampingRatio = Spring.DampingRatioMediumBouncy,
            stiffness = Spring.StiffnessLow
        ),
        label = "scale"
    )

    IconButton(
        onClick = onChecked,
        enabled = enabled,
        modifier = Modifier
            .offset(x = 4.dp, y = 4.dp)
            .scale(scale)
    ) {
        AnimatedContent(
            targetState = checked,
            transitionSpec = {
                fadeIn() togetherWith  fadeOut()
            },
            label = "icon"
        ) { isChecked ->
            Icon(
                imageVector = if (isChecked)
                    Icons.Filled.CheckCircle
                else
                    ImageVector.vectorResource(R.drawable.ic_outlined_circle),
                tint = tint,
                modifier = Modifier
                    .background(background, CircleShape),
                contentDescription = "checkbox"
            )
        }
    }
}





