package com.eyubero.fumblebook.di

import com.eyubero.fumblebook.provider.ApiProvider
import com.eyubero.fumblebook.repository.UserRepository
import com.eyubero.fumblebook.repository.UserRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Singleton
    @Provides
    fun providesUserRepository(apiProvider: ApiProvider): UserRepository = UserRepositoryImpl(apiProvider = apiProvider)
}