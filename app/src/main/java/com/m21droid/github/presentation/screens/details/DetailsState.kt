package com.m21droid.github.presentation.screens.details

import androidx.compose.runtime.MutableState
import com.m21droid.github.domain.models.UserDetailsModel

typealias DetailsStateData = Pair<UserDetailsModel, MutableState<Boolean>>

sealed class DetailsState {

    object Loading : DetailsState()

    object Failure : DetailsState()

    object Empty : DetailsState()

    data class Display(val data: DetailsStateData) : DetailsState()

}