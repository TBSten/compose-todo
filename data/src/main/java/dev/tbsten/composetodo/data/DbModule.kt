package dev.tbsten.composetodo.data

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

  @Provides
  fun provideAppDatabase(
    @ApplicationContext applicationContext: Context
  ) = Room.databaseBuilder(
    applicationContext,
    AppDatabase::class.java, "todo-db"
  ).build()

}