package com.m21droid.github.presentation.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.m21droid.github.domain.models.UserModel

class UsersPreview : PreviewParameterProvider<List<UserModel>> {

    override val values = sequenceOf(
        UserPreview().values.toList()
    )

}