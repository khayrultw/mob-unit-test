package com.kht.mob23location.core.di

import com.kht.mob23location.data.repo.IUserRepo
import com.kht.mob23location.data.repo.UserRepo
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {
    @Provides
    @Singleton
    fun provideUserRepo(): IUserRepo {
        return UserRepo()
    }
}