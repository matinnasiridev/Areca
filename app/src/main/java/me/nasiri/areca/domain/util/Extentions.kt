package me.nasiri.areca.domain.util

import android.annotation.SuppressLint
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.pager.PagerState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.unit.IntSize
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

@OptIn(ExperimentalFoundationApi::class)
fun CoroutineScope.updateState(state: PagerState, action: Int) {
    launch {
        state.animateScrollToPage(action)
    }
}


/**
 * Shimmer Effect Use Custom Code In Compose.
 *  @param[customColorList] You can set Your custom color for effect.
 */
fun Modifier.shimmerEffect(customColorList: List<Color>? = null): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")

    val startOffsetX by transition.animateFloat(
        initialValue = -2 * size.width.toFloat(),
        targetValue = 2 * size.width.toFloat(),
        animationSpec = infiniteRepeatable(tween(durationMillis = 1000)),
        label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = customColorList ?: listOf(
                Color(0xFFB8B5B5),
                Color(0xFF8F8B8B),
                Color(0xFFB8B5B5)
            ),
            start = Offset(startOffsetX, 0f),
            end = Offset(startOffsetX + size.width.toFloat(), size.height.toFloat())
        )
    )
    onGloballyPositioned {
        size = it.size
    }
}

@SuppressLint("UnnecessaryComposedModifier")
fun Modifier.setShimmer(condition: Boolean): Modifier = composed {
    if (condition)
        shimmerEffect()
    this
}

val setDollar: (Any) -> String = {
    if (it is String && it.contains("$"))
        it
    else
        "$$it"
}