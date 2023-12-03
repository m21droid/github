package com.m21droid.github.presentation.previews

import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.m21droid.github.domain.models.UserModel

class UserPreview : PreviewParameterProvider<UserModel> {

    override val values = sequenceOf(
        UserModel(
            login = "mojombo",
            id = 1,
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4"
        ),
        UserModel(
            login = "defunkt",
            id = 2,
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4"
        ),
        UserModel(
            login = "pjhyett",
            id = 3,
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4"
        )
    )

}