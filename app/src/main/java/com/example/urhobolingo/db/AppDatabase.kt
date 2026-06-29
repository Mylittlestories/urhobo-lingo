package com.example.urhobolingo.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.urhobolingo.data.local.AppDao
import com.example.urhobolingo.data.local.FavoriteWordEntity
import com.example.urhobolingo.data.local.LessonCompletionEntity
import com.example.urhobolingo.data.local.ProgressEntity

@Database(
    entities = [ProgressEntity::class, FavoriteWordEntity::class, LessonCompletionEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun appDao(): AppDao
}
