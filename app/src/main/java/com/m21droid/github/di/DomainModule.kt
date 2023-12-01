package com.m21droid.github.di

import com.m21droid.github.domain.repositories.DataRepository
import com.m21droid.github.domain.usecases.GetAllUsersUseCase
import com.m21droid.github.domain.usecases.GetUserUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
class DomainModule {

    @Provides
    fun provideGetAllUsersUseCase(dataRepository: DataRepository): GetAllUsersUseCase {
        return GetAllUsersUseCase(dataRepository)
    }

    @Provides
    fun provideGetUserUseCase(dataRepository: DataRepository): GetUserUseCase {
        return GetUserUseCase(dataRepository)
    }

}