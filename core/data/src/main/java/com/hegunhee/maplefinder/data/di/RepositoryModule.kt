package com.hegunhee.maplefinder.data.di

import com.hegunhee.maplefinder.data.repository.DefaultRepository
import com.hegunhee.maplefinder.domain.repository.Repository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule(){

    @Singleton
    @Binds
    abstract fun bindRepository(
        defaultRepository: DefaultRepository
    ) : Repository

}
