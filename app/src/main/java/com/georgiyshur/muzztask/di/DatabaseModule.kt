package com.georgiyshur.muzztask.di

import android.content.Context
import com.georgiyshur.muzztask.database.MuzzTaskDatabase
import com.georgiyshur.muzztask.database.dao.MessageDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
internal class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): MuzzTaskDatabase {
        return MuzzTaskDatabase.getInstance(appContext)
    }

    @Provides
    fun provideMessageDao(database: MuzzTaskDatabase): MessageDao {
        return database.messageDao()
    }
}
