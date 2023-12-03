package com.m21droid.github.presentation.screens.details

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.usecases.GetUserDetailsUseCase
import com.m21droid.github.logD
import com.m21droid.github.presentation.navigation.NavConst.ARG_LOGIN
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getUserDetailsUseCase: GetUserDetailsUseCase,
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    private val _detailsState = mutableStateOf<DetailsState>(DetailsState.Empty)
    internal val detailsState: State<DetailsState> = _detailsState

    init {
        savedStateHandle.get<String?>(ARG_LOGIN)?.let {
            getDetails(it)
        }
    }

    private fun getDetails(login: String) {
        viewModelScope.launch {
            getUserDetailsUseCase.execute(login).collect {
                logD("getDetails: details - $it")

                _detailsState.value = when (it) {
                    ResponseState.Loading -> DetailsState.Loading
                    is ResponseState.Failure -> DetailsState.Failure
                    is ResponseState.Success -> {
                        val data = it.data
                        DetailsState.Display(data)
                    }
                }
            }
        }
    }

}