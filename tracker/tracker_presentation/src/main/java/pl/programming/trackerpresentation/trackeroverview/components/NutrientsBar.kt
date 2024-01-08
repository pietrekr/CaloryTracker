package pl.programming.trackerpresentation.trackeroverview.components

import androidx.compose.animation.core.Animatable
import androidx.compose.foundation.Canvas
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Size
import pl.programming.coreui.CarbColor
import pl.programming.coreui.FatColor
import pl.programming.coreui.ProteinColor

@Composable
fun NutrientsBar(
    carbs: Int,
    protein: Int,
    fat: Int,
    calories: Int,
    caloriesGoal: Int,
    modifier: Modifier = Modifier,
) {
    val background = MaterialTheme.colors.background
    val caloriesExceededColor = MaterialTheme.colors.error
    val carbWidthRadio = remember {
        Animatable(0f)
    }
    val proteinWidthRadio = remember {
        Animatable(0f)
    }
    val fatWidthRadio = remember {
        Animatable(0f)
    }
    LaunchedEffect(key1 = carbs) {
        carbWidthRadio.animateTo(
            targetValue = ((carbs * 4f) / caloriesGoal),
        )
    }
    LaunchedEffect(key1 = protein) {
        proteinWidthRadio.animateTo(
            targetValue = ((protein * 4f) / caloriesGoal),
        )
    }
    LaunchedEffect(key1 = fat) {
        fatWidthRadio.animateTo(
            targetValue = ((fat * 9f) / caloriesGoal),
        )
    }

    Canvas(modifier = modifier) {
        if (calories <= caloriesGoal) {
            val carbsWidth = carbWidthRadio.value * size.width
            val proteinWidth = proteinWidthRadio.value * size.width
            val fatWidth = fatWidthRadio.value * size.width

            drawRoundRect(
                color = background,
                size = size,
                cornerRadius = CornerRadius(100f),
            )
            drawRoundRect(
                color = FatColor,
                size = Size(
                    width = carbsWidth + proteinWidth + fatWidth,
                    height = size.height,
                ),
                cornerRadius = CornerRadius(100f),
            )
            drawRoundRect(
                color = ProteinColor,
                size = Size(
                    width = carbsWidth + proteinWidth,
                    height = size.height,
                ),
                cornerRadius = CornerRadius(100f),
            )
            drawRoundRect(
                color = CarbColor,
                size = Size(
                    width = carbsWidth,
                    height = size.height,
                ),
                cornerRadius = CornerRadius(100f),
            )
        } else {
            drawRoundRect(
                color = caloriesExceededColor,
                size = size,
                cornerRadius = CornerRadius(100f)
            )
        }
    }
}
