package com.maid.cleanhires.hilt

import android.content.Context
import androidx.room.Room
import com.maid.cleanhires.data.local.room.ServiceDao
import com.maid.cleanhires.data.local.room.ServiceDatabase
import com.maid.cleanhires.repositories.CartRepository
import com.maid.cleanhires.repositories.ServiceRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun databaseService(context: Context) = Room.databaseBuilder(
        context,
        ServiceDatabase::class.java,
        "serviceDB.db"
    ).fallbackToDestructiveMigration()
        .build()

    @Provides
    fun provideContext(@ApplicationContext context: Context) :Context {
        return context
    }

    @Provides
    fun provideDao(serviceDatabase: ServiceDatabase) = serviceDatabase.getDao()

    @Provides
    fun provideRepository(serviceDatabase: ServiceDatabase, context: Context) =
        ServiceRepository(serviceDatabase, context)
    @Provides
    fun provideCartRepository(serviceDatabase: ServiceDatabase) = CartRepository(serviceDatabase)
}