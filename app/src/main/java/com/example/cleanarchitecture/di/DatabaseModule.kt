package com.example.cleanarchitecture.di

import android.app.Application
import androidx.room.Room
import com.example.cleanarchitecture.domain.database.AppDataBase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(
        application: Application
    ): AppDataBase {
        return Room
            .databaseBuilder(application, AppDataBase::class.java, "pokemon.db")
            .fallbackToDestructiveMigration()
            .build()
    }
}