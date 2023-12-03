package com.m21droid.github.presentation.previews

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.m21droid.github.presentation.screens.users.UsersStateData

class UsersPreview : PreviewParameterProvider<UsersStateData> {

    override val values = sequenceOf(
        UserPreview().values.toList().map {
            Pair(it, mutableStateOf(it.id % 2 == 1))
        }
    )

}