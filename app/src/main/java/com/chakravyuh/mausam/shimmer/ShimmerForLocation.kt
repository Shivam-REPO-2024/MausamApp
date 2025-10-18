package com.chakravyuh.mausam.shimmer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import com.valentinilk.shimmer.LocalShimmerTheme
import com.valentinilk.shimmer.ShimmerBounds
import com.valentinilk.shimmer.rememberShimmer
import com.valentinilk.shimmer.shimmer

@Composable
fun LoadTextShimmer(modifier: Modifier = Modifier) {

    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View, theme = LocalShimmerTheme.current)
    Box(
        modifier = modifier
            .fillMaxWidth()
            .widthIn(max = 100.dp)
            .padding(horizontal = 15.dp)
            .height(30.dp)
            .clip(RoundedCornerShape(4.dp))
            .shimmer(shimmerInstance)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

@Composable
fun LoadCircularShimmer(modifier: Modifier = Modifier) {

    val shimmerInstance = rememberShimmer(shimmerBounds = ShimmerBounds.View, theme = LocalShimmerTheme.current)
    Box(
        modifier = modifier

            .padding(all = 30.dp)
            .clip(CircleShape)
            .size(100.dp)
            .shimmer(shimmerInstance)
            .background(MaterialTheme.colorScheme.surfaceVariant)
    )
}

