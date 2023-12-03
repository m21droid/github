package com.m21droid.github.domain.usecases

import com.m21droid.github.domain.models.ResponseState
import com.m21droid.github.domain.repositories.DataRepository
import kotlinx.coroutines.flow.Flow

class GetSelectedUsersUseCase(
    private val dataRepository: DataRepository,
) : BaseUseCase<Unit, Flow<ResponseState<List<Int>>>> {

    override fun execute(input: Unit): Flow<ResponseState<List<Int>>> {
        return dataRepository.getSelectedUsers()
    }

}