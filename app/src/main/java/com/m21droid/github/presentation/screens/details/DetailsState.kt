package com.m21droid.github.presentation.screens.details

import com.m21droid.github.domain.models.UserDetailsModel

sealed class DetailsState {

    object Loading : DetailsState()

    object Failure : DetailsState()

    object Empty : DetailsState()

    data class Display(val data: UserDetailsModel) : DetailsState()

}