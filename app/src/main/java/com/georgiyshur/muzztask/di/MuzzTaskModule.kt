package com.georgiyshur.muzztask.di

import com.georgiyshur.muzztask.chat.data.DatabaseDataSource
import com.georgiyshur.muzztask.chat.data.LocalDataSource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
internal interface MuzzTaskModule {

    @Binds
    fun bindsMessagesLocalDataSource(
        impl: DatabaseDataSource,
    ): LocalDataSource
}
