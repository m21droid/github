package com.m21droid.github.presentation.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.unit.dp
import com.m21droid.github.Const.ERROR
import com.m21droid.github.R
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.presentation.previews.UsersPreview
import com.m21droid.github.presentation.views.MainText
import com.m21droid.github.presentation.views.WhiteText

@Preview
@Composable
fun UsersScreenPreview1(@PreviewParameter(UsersPreview::class) users: List<UserModel>) {
    val state = remember { mutableStateOf(UsersState.DisplayNotSort(users)) }
    UsersScreen(state = state)
}

@Preview
@Composable
fun UsersScreenPreview2(@PreviewParameter(UsersPreview::class) users: List<UserModel>) {
    val state = remember { mutableStateOf(UsersState.DisplaySort(users)) }
    UsersScreen(state = state)
}

@Composable
fun UsersScreen(
    state: State<UsersState>,
    onClickSort: () -> Unit = {},
    onClickItem: (UserModel) -> Unit = {},
) {
    val space = 12.dp
    val value = state.value
    Scaffold(
        topBar = {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(56.dp)
                    .background(colorResource(id = R.color.purple_200))
                    .padding(16.dp)
            ) {
                WhiteText(
                    text = "Користувачі GitHub",
                    modifier = Modifier.align(Alignment.CenterStart)
                )
                val color = when (value) {
                    is UsersState.DisplayNotSort -> Color.White
                    is UsersState.DisplaySort -> Color.Yellow
                    else -> null
                }
                if (color != null) {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_sort_by_alpha_24),
                        contentDescription = null,
                        modifier = Modifier
                            .align(Alignment.CenterEnd)
                            .clickable(onClick = onClickSort),
                        colorFilter = ColorFilter.tint(color)
                    )
                }
            }
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .padding(16.dp),
            contentAlignment = Alignment.Center
        ) {
            when (value) {
                UsersState.Loading -> {
                    CircularProgressIndicator()
                }

                UsersState.Failure -> {
                    MainText(text = ERROR)
                }

                UsersState.Empty -> {
                    MainText(text = "Немає користувачів")
                }

                is UsersState.DisplayNotSort -> {
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(space)
                    ) {
                        items(value.users.size) { index ->
                            val user = value.users[index]
                            UserItem(
                                user = user,
                                modifier = Modifier.clickable {
                                    onClickItem(user)
                                }
                            )
                        }
                    }
                }

                is UsersState.DisplaySort -> {
                    val users = value.users.sortedBy { user ->
                        user.login.lowercase()
                    }
                    LazyColumn(
                        modifier = Modifier.fillMaxSize(),
                        verticalArrangement = Arrangement.spacedBy(space)
                    ) {
                        items(users.size) { index ->
                            val user = users[index]
                            val first = user.login.first().uppercase()
                            if (index == 0 || first != users[index - 1].login.first().uppercase()) {
                                MainText(text = first, modifier = Modifier.padding(bottom = space))
                            }
                            UserItem(
                                user = user,
                                modifier = Modifier.clickable {
                                    onClickItem(user)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}