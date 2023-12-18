package com.m21droid.github.presentation.screens.users

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import com.m21droid.github.R
import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.presentation.Const.ERROR
import com.m21droid.github.presentation.previews.UsersPreview
import com.m21droid.github.presentation.views.AppTopAppBar
import com.m21droid.github.presentation.views.HeaderLazyColumn
import com.m21droid.github.presentation.views.MainText
import com.m21droid.github.presentation.views.PrimaryText
import com.m21droid.github.presentation.views.space

@Preview
@Composable
fun UsersScreenPreview1(@PreviewParameter(UsersPreview::class) data: UsersStateData) {
    val state = remember { mutableStateOf(UsersState.Display(data)) }
    UsersScreen(state = state)
}

@Preview
@Composable
fun UsersScreenPreview2(@PreviewParameter(UsersPreview::class) data: UsersStateData) {
    val state = remember { mutableStateOf(UsersState.DisplaySort(data)) }
    UsersScreen(state = state)
}

@Composable
fun UsersScreen(
    state: State<UsersState>,
    onClickSort: () -> Unit = {},
    onClickItem: (Pair<UserModel, MutableState<Boolean>>) -> Unit = {},
) {
    val value = state.value
    Scaffold(
        topBar = {
            AppTopAppBar(
                title = {
                    Text(text = "Користувачі GitHub")
                },
                actions = {
                    val color = when (value) {
                        is UsersState.Display -> Color.White
                        is UsersState.DisplaySort -> Color.Yellow
                        else -> null
                    }
                    if (color != null) {
                        Image(
                            imageVector = ImageVector.vectorResource(id = R.drawable.ic_sort_by_alpha_24),
                            contentDescription = null,
                            modifier = Modifier.clickable(onClick = onClickSort),
                            colorFilter = ColorFilter.tint(color)
                        )
                    }
                }
            )
        }
    ) {
        Box(
            modifier = Modifier
                .padding(it)
                .fillMaxSize(),
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

                is UsersState.Display -> {
                    HeaderLazyColumn {
                        items(value.data.size) { index ->
                            val element = value.data[index]
                            UserItem(
                                user = element.first,
                                state = element.second,
                                modifier = Modifier.clickable {
                                    onClickItem(element)
                                }
                            )
                        }
                    }
                }

                is UsersState.DisplaySort -> {
                    val data = value.data.sortedBy { user ->
                        user.first.login.lowercase()
                    }
                    HeaderLazyColumn {
                        items(data.size) { index ->
                            val element = data[index]
                            val user = element.first
                            val first = user.login.first().uppercase()
                            if (index == 0 ||
                                first != data[index - 1].first.login.first().uppercase()
                            ) {
                                PrimaryText(
                                    text = first,
                                    modifier = Modifier.padding(bottom = space)
                                )
                            }
                            UserItem(
                                user = user,
                                state = element.second,
                                modifier = Modifier.clickable {
                                    onClickItem(element)
                                }
                            )
                        }
                    }
                }
            }
        }
    }
}