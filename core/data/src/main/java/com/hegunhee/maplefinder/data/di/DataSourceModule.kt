package com.hegunhee.maplefinder.data.di

import com.hegunhee.maplefinder.data.api.datasource.remote.DefaultRemoteDataSource
import com.hegunhee.maplefinder.data.api.datasource.remote.RemoteDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class DataSourceModule {

    @Singleton
    @Binds
    abstract fun bindRemoteDataSource(
        defaultRemoteDataSource: DefaultRemoteDataSource
    ) : RemoteDataSource

}