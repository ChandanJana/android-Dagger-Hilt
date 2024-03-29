package com.test.hiltapplication.di

import android.content.Context
import androidx.room.Room
import com.test.hiltapplication.data.local.ImageDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * Created by Chandan Jana on 21-08-2023.
 * Company name: Mindteck
 * Email: chandan.jana@mindteck.com
 */
@Module
@InstallIn(SingletonComponent::class)
object DBModule {

    @Singleton
    @Provides // declares scoped singleton
    fun provideAppDatabase(@ApplicationContext context: Context): ImageDatabase {
        return Room.databaseBuilder(
            context,
            ImageDatabase::class.java,
            "app_db.db"
        )
            .allowMainThreadQueries()
            .build()
    }
}
