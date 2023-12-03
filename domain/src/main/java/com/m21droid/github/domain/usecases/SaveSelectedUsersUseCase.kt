package com.m21droid.github.domain.usecases

import com.m21droid.github.domain.models.UserModel
import com.m21droid.github.domain.repositories.DataRepository

class SaveSelectedUsersUseCase(
    private val dataRepository: DataRepository,
) : BaseUseCase<List<UserModel>, Unit> {

    override fun execute(input: List<UserModel>) {
        return dataRepository.saveSelectedUsers(input)
    }

}