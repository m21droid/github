package com.m21droid.github.presentation.views

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color.Companion.Black
import androidx.compose.ui.graphics.Color.Companion.LightGray
import androidx.compose.ui.graphics.Color.Companion.White
import androidx.compose.ui.unit.dp
import com.m21droid.github.Const.padding
import kotlin.math.min

@Composable
fun PreviewBox(content: @Composable BoxScope.() -> Unit) {
    Box(
        modifier = Modifier
            .background(White)
            .padding(padding),
        content = content
    )
}

@Composable
fun PreviewColumn(content: @Composable ColumnScope.() -> Unit) {
    Column(
        modifier = Modifier
            .background(LightGray)
            .padding(padding),
        verticalArrangement = Arrangement.spacedBy(24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        content = content
    )
}

@Composable
fun PreviewItem(index: Int, direction: Boolean = false) {
    val big = (286 - 10 * min(28, index)).dp
    val small = 56.dp
    val width = if (direction) small else big
    val height = if (direction) big else small
    Box(
        modifier = Modifier
            .width(width)
            .height(height)
            .background(Black.copy(red = 0.1f * min(10, index)))
    )
}