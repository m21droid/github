package com.m21droid.github.presentation.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.NestedScrollConnection
import androidx.compose.ui.input.nestedscroll.NestedScrollSource
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.m21droid.github.R
import com.m21droid.github.logD

val toolbarHeight = 192.dp
val buttonSize = toolbarHeight / 4
val buttonOffset = buttonSize / 2
val buttonAlpha = toolbarHeight / 2
val space = 16.dp

@Preview
@Composable
fun HeaderLazyColumnPreview() {
    HeaderLazyColumn {
        items(10) {
            PreviewItem(index = it)
        }
    }
}

@Composable
fun HeaderLazyColumn(content: LazyListScope.() -> Unit) {
    val toolbarHeightPx = with(LocalDensity.current) { toolbarHeight.roundToPx().toFloat() }
    var toolbarOffsetPx by remember { mutableFloatStateOf(0f) }

    val nestedScrollConnection = remember {
        object : NestedScrollConnection {
            override fun onPreScroll(available: Offset, source: NestedScrollSource): Offset {
                toolbarOffsetPx = minOf(toolbarOffsetPx + available.y, 0f)
                return Offset.Zero
            }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(nestedScrollConnection)
    ) {
        val height = with(LocalDensity.current) {
            (toolbarHeightPx + toolbarOffsetPx).coerceIn(0f, toolbarHeightPx).toDp()
        }

        // Header
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(height)
                .background(MaterialTheme.colorScheme.primary),
            contentAlignment = Alignment.Center
        ) {
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier.size(toolbarHeight / 4 * 3),
                contentScale = ContentScale.Crop
            )
        }

        // List
        LazyColumn(
            modifier = Modifier
                .offset(y = height)
                .fillMaxSize(),
            contentPadding = PaddingValues(16.dp),
            verticalArrangement = Arrangement.spacedBy(space),
            content = content
        )

        // Button
        val top = maxOf(0.dp, height - buttonOffset)
        if (top <= 0.dp) {
            return
        }
        val alpha = if (top < buttonAlpha) top / buttonAlpha else 1f
        Box(
            modifier = Modifier
                .alpha(alpha)
                .fillMaxWidth()
                .padding(top = top, end = buttonOffset)
        ) {
            val color = MaterialTheme.colorScheme.primary
            Image(
                painter = painterResource(R.drawable.logo),
                contentDescription = null,
                modifier = Modifier
                    .size(buttonSize)
                    .align(Alignment.TopEnd)
                    .background(
                        brush = Brush.verticalGradient(listOf(Color.White, color))
                    )
                    .border(
                        width = 2.dp,
                        brush = Brush.verticalGradient(listOf(Color.White, color, color)),
                        shape = RectangleShape
                    )
                    .clickable {
                        logD("HeaderLazyColumn: ")
                    }
            )
        }
    }
}