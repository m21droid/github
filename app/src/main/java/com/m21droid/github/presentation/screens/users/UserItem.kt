package com.m21droid.github.presentation.screens.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.graphics.vector.rememberVectorPainter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.m21droid.github.R
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.presentation.previews.UserPreview

@Preview
@Composable
fun UserItemPreview(@PreviewParameter(UserPreview::class) user: UserModel) {
    val state = remember { mutableStateOf(user.id % 2 == 1) }
    UserItem(user = user, state = state)
}

@Composable
fun UserItem(
    user: UserModel,
    state: MutableState<Boolean>,
    modifier: Modifier = Modifier,
) {
    val size = 64.dp
    Row(
        modifier = modifier.height(size),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val image = ImageVector.vectorResource(id = R.drawable.ic_account_circle_64)
        AsyncImage(
            model = ImageRequest.Builder(LocalContext.current)
                .data(user.avatarUrl)
                .build(),
            contentDescription = null,
            modifier = Modifier
                .size(size)
                .clip(CircleShape),
            placeholder = rememberVectorPainter(image = image),
            error = rememberVectorPainter(image = image),
            contentScale = ContentScale.Fit,
        )
        Text(modifier = Modifier.weight(1.0f), text = user.login)
        Icon(
            Icons.Filled.Star,
            contentDescription = null,
            modifier = Modifier.clickable {
                state.value = !state.value
            },
            tint = if (state.value) Color.Yellow else Color.LightGray
        )
    }
}