package com.m21droid.github.domain.usecases

import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.models.UserDetailsModel
import com.m21droid.github.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow

class GetUserDetailsUseCase(
    private val dataRepository: DataRepository,
) : BaseUseCase<String, Flow<ResponseState<UserDetailsModel>>> {

    override fun execute(input: String): Flow<ResponseState<UserDetailsModel>> {
        return dataRepository.getUserDetails(input)
    }

}