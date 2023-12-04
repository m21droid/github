package com.m21droid.github.presentation.previews

import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import com.m21droid.github.domain.models.UserDetailsModel
import com.m21droid.github.presentation.screens.details.DetailsStateData

class DetailsPreview : PreviewParameterProvider<DetailsStateData> {

    override val values = sequenceOf(
        UserDetailsModel(
            login = "mojombo",
            id = 1,
            avatarUrl = "https://avatars.githubusercontent.com/u/1?v=4",
            name = "First Last",
            location = "Kremenchuk"
        ),
        UserDetailsModel(
            login = "defunkt",
            id = 2,
            avatarUrl = "https://avatars.githubusercontent.com/u/2?v=4",
            name = "First Last",
            location = "Kremenchuk"
        ),
        UserDetailsModel(
            login = "pjhyett",
            id = 3,
            avatarUrl = "https://avatars.githubusercontent.com/u/3?v=4",
            name = "First Last",
            location = "Kremenchuk"
        )
    ).map {
        Pair(it, mutableStateOf(it.id % 2 == 1))
    }

}